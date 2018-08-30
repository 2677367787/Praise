package com.company.project.core;

import com.company.project.model.Praise;
import com.company.project.model.Users;
import com.company.project.service.UsersService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;

public class EmaliUtils {
	@Resource


	@Value("${web.email.path}")
	private String emailUrl;

	public static void send(){
		final String url = "";
		HttpHelper.post("",url);

	}

	public static void praiseAdd(String from,String to,List<Praise> top3List,List<Users> usersList){

		StringBuilder cbText = new StringBuilder();
		top3List.forEach(top->{
			cbText.append("<table><tr><td>第一名</td><td>").append(top.getUniqueName()).append("</td></tr>");
			cbText.append("<tr><td>第二名</td><td>").append(top.getUniqueName()).append("</td></tr>");
			cbText.append("<tr><td>第三名</td><td>").append(top.getUniqueName()).append("</td></tr></table>");
		});
	}
}
