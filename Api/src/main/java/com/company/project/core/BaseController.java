package com.company.project.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	
    @Autowired
    protected HttpServletRequest request;

     
    protected  Integer getUserId(){
    	Object userId = request.getAttribute("userId");
        return userId == null? 0 : Integer.parseInt(userId.toString());
    }
    
    protected  String getUserName(){
    	Object userName = request.getAttribute("userName");
        return userName == null? "" : userName.toString();
    }
    
    protected  String getNickName(){
    	Object nickName = request.getAttribute("nickName");
        return nickName == null? "" : nickName.toString();
    }
}
