package com.company.project.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.project.core.Result;
import com.company.project.model.Users;
import com.company.project.service.UsersService;

/**
 * 文件上传
 * 
 * @author Fly
 *
 */
@RestController
@RequestMapping("/users")
public class FileUpload {
	/**
	 * 文件上传根目录(在Spring的application.yml的配置文件中配置):<br>
	 * web: upload-path: （jar包所在目录）/resources/static/
	 */
	@Value("${web.upload-path}")
	private String webUploadPath;
	
    @Resource
    private UsersService usersService;

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
			int userId = 6;
			if (file.getContentType().contains("image")) {
				try {
					String temp = "images" + File.separator + "upload" + File.separator;
					// 获取图片的文件名
					String fileName = file.getOriginalFilename();
					// 获取图片的扩展名
					String extensionName = StringUtils.substringAfter(fileName, ".");
					// 新的图片文件名 = 获取时间戳+"."图片扩展名
					String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
					// 数据库保存的目录
					String datdDirectory = temp.concat(String.valueOf(userId)).concat(File.separator);
					// 文件路径
					String filePath = webUploadPath.concat(datdDirectory);

					File dest = new File(filePath, newFileName);
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					// 判断是否有旧头像，如果有就先删除旧头像，再上传
					Users userInfo = usersService.findById(userId);
					if (StringUtils.isNotBlank(userInfo.getPortrait())) {
						String oldFilePath = webUploadPath.concat(userInfo.getId().toString());
						File oldFile = new File(oldFilePath);
						if (oldFile.exists()) {
							oldFile.delete();
						}
					}
					// 上传到指定目录
					file.transferTo(dest);

					// 将图片流转换进行BASE64加码
					// BASE64Encoder encoder = new BASE64Encoder();
					// String data = encoder.encode(file.getBytes());

					// 将反斜杠转换为正斜杠
					String data = datdDirectory.replaceAll("\\\\", "/") + newFileName;
					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("file", data);
					result.setData(resultMap);
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

}