package com.ioxmentor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ioxmentor.entity.User;
import com.ioxmentor.repo.UserRepo;

/** created By @Nabeel 08-Oct-2017 **/

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value="/user")
public class RestController {
	
	@Autowired
	private UserRepo userRepo;
	
	@RequestMapping(value="/signup")
	public void signUp(@RequestParam String name,@RequestParam String email,@RequestParam String password)
	{
		User user=new User();
		user.setEmail(email);
		user.setIsActive(1);
		user.setPassword(password);
		user.setUserName(name);
		userRepo.save(user);
		System.out.println("done insert");
	}
	
}
