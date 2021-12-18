package com.designbay.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.designbay.dao.UserDao;
import com.designbay.entities.User;

@Controller
public class SignupController {
	
	@RequestMapping("/signup")
	public String showForm() {
		return "signup";
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(path="/processform", method=RequestMethod.POST)
	public String handleForm(
			@RequestParam("fullName") String fullName, 
			@RequestParam("username") String username, 
			@RequestParam("emailId") String emailId, 
			@RequestParam("password") String password) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		User user = new User(fullName, username, emailId, password);
		int r = userDao.insert(user);
		System.out.println("Done " + r);
		return "index";
	}
}
