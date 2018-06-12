package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.UsersMapper;
import com.company.project.model.Users;
import com.company.project.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2018/04/14
 */
@Service
@Transactional
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
    @Resource
    private UsersMapper usersMapper;
    
    @Override
    public void updateUser(Users users) {
    	usersMapper.updateUser(users);
    }

	/**
	 * 返回所有用户信息,去掉敏感信息
	 * @return
	 */
	@Override
	public List<Users> getAllUser(Users users) {
		return usersMapper.getAllUser(users);
	}
}
