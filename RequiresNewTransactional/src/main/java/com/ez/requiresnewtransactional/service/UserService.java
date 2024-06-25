package com.ez.requiresnewtransactional.service;

import com.ez.requiresnewtransactional.bean.User;

/**
 * @Classname UserService
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/24
 */
public interface UserService {

	void addUserA(User userA, User userB);

	void addUserB(User user);
}
