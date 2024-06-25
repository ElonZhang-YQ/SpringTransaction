package com.ez.notsupportedtransactional.service;

import com.ez.notsupportedtransactional.bean.User;
import com.ez.notsupportedtransactional.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname UserServiceB
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/24
 */
@Service
@Transactional
public class UserServiceB implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void addUserA(User userA, User userB) {

		// DO NOTHING in Service B
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void addUserB(User user) {

		userMapper.insertUserB(user);
		System.out.println("执行B");
	}
}
