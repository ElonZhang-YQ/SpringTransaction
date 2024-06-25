package com.ez.notsupportedtransactional.controller;

import com.ez.notsupportedtransactional.bean.User;
import com.ez.notsupportedtransactional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/24
 */
@RestController
@RequestMapping("/required")
public class UserController {

	@Autowired
	private UserService userServiceA;

	@RequestMapping("/add")
	public String addUser() {
		userServiceA.addUserA(new User(1, "T1"), new User(2, "T2"));
		return "SUCCESS";
	}
}
