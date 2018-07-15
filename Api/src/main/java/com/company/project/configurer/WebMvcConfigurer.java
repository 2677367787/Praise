package com.company.project.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.company.project.core.ProcessInterceptor;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ServiceException;
import com.company.project.core.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Spring MVC 配置
 * @author tanggzhi
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

	/**
	 * 当前激活的配置文件
	 */
	@Value("${spring.profiles.active}")
	private String env;

	/**
	 * 使用阿里 FastJson 作为JSON MessageConverter
	 * @param converters 参数
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(
				// 保留空的字段
				SerializerFeature.WriteMapNullValue,
				// String null -> ""
				SerializerFeature.WriteNullStringAsEmpty,
				// Number null -> 0
				SerializerFeature.WriteNullNumberAsZero);
		converter.setFastJsonConfig(config);

		converter.setDefaultCharset(Charset.forName("UTF-8"));
		converters.add(converter);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

	/**
	 * 统一异常处理
	 * @param exceptionResolvers 异常信息
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add((request, response, handler, e) -> {
			Result result = new Result();
			// 业务失败的异常，如“账号或密码错误”
			if (e instanceof ServiceException) {
				result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
				logger.info(e.getMessage());
			} else if (e instanceof NoHandlerFoundException) {
				result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
			} else if (e instanceof ServletException) {
				result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
			} else {
				result.setCode(ResultCode.INTERNAL_SERVER_ERROR)
						.setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
				String message;
				if (handler instanceof HandlerMethod) {
					HandlerMethod handlerMethod = (HandlerMethod) handler;
					message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
							handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(),
							e.getMessage());
				} else {
					message = e.getMessage();
				}
				logger.error(message, e);
			}
			responseResult(response, result);
			return new ModelAndView();
		});
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// registry.addMapping("/**");
	}

	/**
	 * 添加拦截器,拦截所有请求,验证是否登录了
	 * @param registry 主体
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ProcessInterceptor());
		registry.addInterceptor(new HandlerInterceptorAdapter() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
				// 验证签名
				String token = request.getHeader("t-token");
				System.out.println(token);
				System.out.println(request.getRequestURI());
				if (token == null) {
					return requestFail(request, response);
				}

				try {
					Claims claims = JwtUtil.parseJWT(token);
					if (claims != null) {
						String jsonStr = claims.getSubject();
						System.out.println(jsonStr);

						JSONObject user = JSONObject.parseObject(jsonStr);
						request.setAttribute("nickName", user.get("nickName"));
						request.setAttribute("userName", user.get("userName"));
						request.setAttribute("userId", user.get("userId"));
						return true;
					} else {
						return requestFail(request, response);
					}
				} catch (Exception ex) {
					return requestFail(request, response);
				}
			}
		}).addPathPatterns("/**").excludePathPatterns("/images/**")
                .excludePathPatterns("/users/login")
                .excludePathPatterns("/public/**")
                .excludePathPatterns("/users/rest/**");

		super.addInterceptors(registry);
	}

	private boolean requestFail(HttpServletRequest request, HttpServletResponse response) {
		logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), getIpAddress(request),
				JSON.toJSONString(request.getParameterMap()));
		Result result = new Result();
		result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
		response.setHeader("Access-Control-Allow-Origin", "*");
		responseResult(response, result);
		return false;
	}

	private void responseResult(HttpServletResponse response, Result result) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setStatus(200);
		try {
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}
 
	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 如果是多级代理，那么取第一个ip为客户端ip
		if (ip != null && ip.contains(",")) {
			ip = ip.substring(0, ip.indexOf(",")).trim();
		}

		return ip;
	}
}
