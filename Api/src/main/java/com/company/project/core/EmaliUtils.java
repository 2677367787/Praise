package com.company.project.core;

import com.company.project.model.Users;
import com.company.project.service.UsersService;

import javax.annotation.Resource;
import java.util.List;

public class EmaliUtils {
	@Resource
	private UsersService usersService;

	public void send(){
		final String url = "";
		HttpHelper.post("",url);

		List<Users> usersList = usersService.getAllUser(null);

	}
}
