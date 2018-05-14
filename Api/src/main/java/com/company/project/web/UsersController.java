package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.core.jwt.JwtUtil;
import com.company.project.model.ImpressTag;
import com.company.project.model.Users;
import com.company.project.service.UsersService;
import tk.mybatis.mapper.entity.Condition;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tang zhi on 2018/04/14.
 */
@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {
	@Resource
	private UsersService usersService;

	private final Logger logger = LoggerFactory.getLogger(UsersController.class);
	/**
	 * 文件上传根目录(在Spring的application.yml的配置文件中配置):<br>
	 * web: upload-path: （jar包所在目录）/resources/static/
	 */
	@Value("${web.upload-path}")
	private String webUploadPath;

	/**
	 * 基于用户标识的头像上传
	 * 
	 * @param file
	 *            图片
	 * @param userId
	 *            用户标识
	 * @return
	 */
	@PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Result fileUpload(@RequestParam("file") MultipartFile file) {
		Result result = new Result();
		if (!file.isEmpty()) {
			int userId = this.getUserId();
			if (file.getContentType().contains("image")) {
				try {
					// 获取图片的文件名
					String fileName = file.getOriginalFilename();
					// 获取图片的扩展名
					String extensionName = StringUtils.substringAfter(fileName, ".");
					// 新的图片文件名 = 用户ID+"."图片扩展名
					String newFileName = this.getUserId() + "." + extensionName;
					
					// 文件路径
					String filePath = webUploadPath;

					File dest = new File(filePath, newFileName);
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					// 判断是否有旧头像，如果有就先删除旧头像，再上传
					Users userInfo = usersService.findById(userId);
					userInfo.setPortrait(newFileName);
					usersService.update(userInfo);
					
//					if (StringUtils.isNotBlank(userInfo.getPortrait())) {
//						String oldFilePath = webUploadPath.concat(userInfo.getId().toString());
//						File oldFile = new File(oldFilePath);
//						if (oldFile.exists()) {
//							oldFile.delete();
//						}
//					}
					// 上传到指定目录
					file.transferTo(dest);

					// 将图片流转换进行BASE64加码
					// BASE64Encoder encoder = new BASE64Encoder();
					// String data = encoder.encode(file.getBytes());

					// 将反斜杠转换为正斜杠
					result.setMessage("上传成功!");
				} catch (IOException e) {
					result.setMessage("上传失败!");
				}
			} else {
				result.setMessage("上传的文件不是图片类型，请重新上传!");
			}
			return result;
		} else {
			// resultVo.setError(0, "上传失败，请选择要上传的图片!");
			return result;
		}
	}

	@PostMapping
	public Result add(@RequestBody Users users) {
		usersService.save(users);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		usersService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody Users users) {
		users.setId(this.getUserId());
		usersService.update(users);
		return ResultGenerator.genSuccessResult();
	}

	@GetMapping("/{param}")
	public Result getUserName(@PathVariable String param) {
		Condition condition = new Condition(Users.class);
		condition.createCriteria().andLike("userName", "%" + param + "%").orLike("nickName", "%" + param + "%");
		List<Users> list = usersService.findByCondition(condition);
		return ResultGenerator.genSuccessResult(list);
	}

	@GetMapping("/info/{userName}")
	public Result list(@PathVariable String userName) {
    	Condition condition = new Condition(Users.class);
		condition.createCriteria().andEqualTo("userName", userName);
		List<Users> list = usersService.findByCondition(condition);
		Users user = list.get(0); 
		user.setPassword("");
		return ResultGenerator.genSuccessResult(user);
	}

	@PostMapping("/login")
	public Result login(@RequestBody Users users) {
		Result result = new Result();
		try {
			logger.debug("---------------------------------------------------");
			Condition condition = new Condition(Users.class);
			condition.createCriteria().andEqualTo("userName", users.getUserName());
			List<Users> list = usersService.findByCondition(condition);
			if (list.size() == 1) {
				String paaword = DigestUtils.md5Hex(users.getPassword().getBytes());
				paaword = DigestUtils.md5Hex((users.getUserName() + paaword).getBytes());
				if (list.get(0).getPassword().equals(paaword)) {
					String id = "wwww.tangzhi.com";
					String token = JwtUtil.createJWT(id, JwtUtil.generalSubject(list.get(0)), 1000L * 60L * 60L * 24L);
					Map<String, String> map = new HashMap<>(5);
					map.put("token", token);
					map.put("userId", list.get(0).getId().toString());
					map.put("userName", list.get(0).getUserName());
					map.put("nickName", list.get(0).getNickName());
					result.setData(map);
					result.setCode(ResultCode.SUCCESS);
				} else {
					result.setCode(ResultCode.FAIL);
					result.setMessage("用户名或密码错误!");
				}
			} else {
				result.setCode(ResultCode.FAIL);
				result.setMessage("用户名不存在!");
			}
		} catch (Exception e) {
			result.setCode(ResultCode.FAIL);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@PostMapping("/modifyPassword")
	public Result modifyPassword(@RequestBody Map<String, Object> reqMap) {
		Users user = usersService.findById(this.getUserId());
		String passwordOld = reqMap.get("passwordOld").toString();
		String passwordNew = reqMap.get("passwordNew").toString();

		if (!user.getPassword().equals(getMd5PassWord(passwordOld))) {
			return ResultGenerator.genFailResult("旧密码输入不正确！");
		}

		user.setPassword(getMd5PassWord(passwordNew));
		usersService.update(user);
		return ResultGenerator.genSuccessResult();
	}

	private String getMd5PassWord(String password) {
		String md5Password = DigestUtils.md5Hex(password.getBytes());
		md5Password = DigestUtils.md5Hex((this.getUserName() + md5Password).getBytes());
		return md5Password;
	}

}
