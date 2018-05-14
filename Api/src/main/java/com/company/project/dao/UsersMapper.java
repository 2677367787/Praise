package com.company.project.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.model.Users;

public interface UsersMapper extends Mapper<Users> {
	List<Users> getUsers();

	void update(Users users);
}