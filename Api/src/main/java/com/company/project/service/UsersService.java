package com.company.project.service;
import com.company.project.model.Users;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by tang zhi on 2018/04/14.
 */
public interface UsersService extends Service<Users> {
	List<Users> getUsers();
	void update(Users user);
}
