package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Users;
import com.company.project.service.UsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tanggzhi
 */
@RestController
@RequestMapping("/public")
public class PublicController extends BaseController {
    @Resource
    private UsersService usersService;

    @GetMapping("/valid/{userName}")
    public Result validUsername(@PathVariable String userName) {
        Condition condition = new Condition(Users.class);
        condition.createCriteria().andEqualTo("userName", userName);
        List<Users> list = usersService.findByCondition(condition);
        if(list.size() > 0) {
            return ResultGenerator.genFailResult("用户名已存在!");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/register")
    public Result add(@RequestBody Users users) {
        String password = users.getPassword();
        String userName = users.getUserName();
        String md5Password = DigestUtils.md5Hex(password.getBytes());
        md5Password = DigestUtils.md5Hex((userName + md5Password).getBytes());
        users.setPassword(md5Password);
        usersService.save(users);
        return ResultGenerator.genSuccessResult();
    }
}
