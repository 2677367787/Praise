package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Users;

import java.util.List;

/**
 * @author tangzhi
 */
public interface UsersMapper extends Mapper<Users> {

	/**
	 * 更新用户信息
	 * @param users 对象
	 */
	void updateUser(Users users);

	/**
	 * 返回所有用户信息,去掉敏感信息
	 * @return user 集合
	 * @param users 对象
	 */
	List<Users> getAllUser(Users users);
}