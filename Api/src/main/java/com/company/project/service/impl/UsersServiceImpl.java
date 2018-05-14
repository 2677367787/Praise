package com.company.project.service.impl;

import com.company.project.dao.UsersMapper;
import com.company.project.model.Users;
import com.company.project.service.UsersService;
import com.company.project.core.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2018/04/14.
 */
@Service
@Transactional
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
    @Resource
    private UsersMapper usersMapper;
     
    public List<Users> getUsers(){
    	return usersMapper.getUsers();
    }
    
    @Override
    public void update(Users users) {
    	usersMapper.update(users);
    }
}
