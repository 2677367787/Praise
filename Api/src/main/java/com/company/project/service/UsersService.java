package com.company.project.service;
import com.company.project.core.Service;
import com.company.project.model.Users;

import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2018/04/14
 */
public interface UsersService extends Service<Users> {

	/**
	 * 更新用户信息
	 * @param user 对象
	 */
	void updateUser(Users user);

	/**
	 * 返回所有用户信息,去掉敏感信息
	 * @return 列表信息
	 * @param users 对象
	 */
	List<Users> getAllUser(Users users);
}
