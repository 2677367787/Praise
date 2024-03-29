package com.company.project.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tanggzhi
 * @deprecated swagger2配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				//选择controller包
				.apis(RequestHandlerSelectors.basePackage("com.company.project.web"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//自定义信息可按需求填写
				.title("Spring Boot中使用Swagger构建RESTful APIs")
				.description("测试")
				.termsOfServiceUrl("http://www.duanxiaowei.top")
				.contact("刘林林")
				.version("1.0")
				.build();
	}
}
