package com.ez.requiresnewtransactional.service;

import com.ez.requiresnewtransactional.bean.User;
import com.ez.requiresnewtransactional.mapper.UserMapper;
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
public class UserServiceB implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public void addUserA(User userA, User userB) {

		// DO NOTHING in Service B
	}

	// @Override
	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	// public void addUserB(User user) {
	//
	// 	userMapper.insertUserB(user);
	// }

	/**
	 * 此时验证子类抛出异常，父类能否正常提交
	 * @param user
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addUserB(User user) {

		userMapper.insertUserB(user);
		throw new RuntimeException("Requires_NEW 方法B 抛出异常");
	}
}
