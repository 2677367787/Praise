package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.core.jwt.JwtUtil;
import com.company.project.model.Users;
import com.company.project.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;  
import java.nio.file.Files;  
import java.nio.file.Paths;  
import java.util.stream.Collectors;  
  
  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.http.ResponseEntity;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.ResponseBody;  
/**
* Created by tang zhi on 2018/04/14.
*/
@RestController
@RequestMapping("/users")
public class UsersController extends BaseController{
    @Resource
    private UsersService usersService;
    
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
        usersService.update(users);
        return ResultGenerator.genSuccessResult();
    }
 
    @GetMapping("/{param}")
    public Result getUserName(@PathVariable String param) {
    	Condition condition = new Condition(Users.class);
    	condition.createCriteria().andLike("userName", "%" + param + "%").orLike("nickName","%" + param + "%");
    	List<Users> list = usersService.findByCondition(condition);
    	return ResultGenerator.genSuccessResult(list);
    }
    
    @GetMapping("/info")
    public Result list() {
    	Users user = usersService.findById(this.getUserId());
    	user.setPassword("");
        return ResultGenerator.genSuccessResult(user);
    }
    
    @PostMapping("/login")
    public Result login(@RequestBody Users users) {
    	Result result = new Result();
    	try {
	    	Condition condition = new Condition(Users.class);
	    	condition.createCriteria().andEqualTo("userName", users.getUserName());  
	    	List<Users> list = usersService.findByCondition(condition);
	    	if(list.size() == 1) {
	    		String paaword = DigestUtils.md5Hex(users.getPassword().getBytes());
	    		paaword = DigestUtils.md5Hex((users.getUserName()+paaword).getBytes());
	    		if(list.get(0).getPassword().equals(paaword)) {
	    			String id = "wwww.tangzhi.com";
    				String token = JwtUtil.createJWT(id, JwtUtil.generalSubject(list.get(0)), 1000L * 60L * 60L * 24L);
					Map<String,String> map = new HashMap<>(5);
					map.put("token", token);
					map.put("userId", list.get(0).getId().toString());
					map.put("userName", list.get(0).getUserName());
					map.put("nickName", list.get(0).getNickName()); 
	    			result.setData(map);
	    			result.setCode(ResultCode.SUCCESS);
	    		}else {
	    			result.setCode(ResultCode.FAIL);
	    			result.setMessage("用户名或密码错误!");
	    		}
	    	}else {
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
    public Result modifyPassword(@RequestBody Map<String,Object> reqMap) {
    	Users user = usersService.findById(this.getUserId());
    	String passwordOld = reqMap.get("passwordOld").toString();
    	String passwordNew = reqMap.get("passwordNew").toString();
    	
		if(!user.getPassword().equals(getMd5PassWord(passwordOld))) {
			return ResultGenerator.genFailResult("旧密码输入不正确！");
		} 
		
		user.setPassword(getMd5PassWord(passwordNew));
        usersService.update(user);
        return ResultGenerator.genSuccessResult();
    }
    
    private String getMd5PassWord(String password) {
    	String md5Password = DigestUtils.md5Hex(password.getBytes());
    	md5Password = DigestUtils.md5Hex((this.getUserName()+md5Password).getBytes());
    	return md5Password;
    }
  
}
