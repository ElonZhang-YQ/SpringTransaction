package com.ez.nestedtransactional.service;

import com.ez.nestedtransactional.bean.User;
import com.ez.nestedtransactional.mapper.UserMapper;
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

	// @Override
	// @Transactional(propagation = Propagation.NESTED)
	// public void addUserB(User user) {
	//
	// 	userMapper.insertUserB(user);
	// }

	/**
	 * 事务：Nested
	 * 测试子方法抛出异常，父方法捕获和不捕获回滚结果
	 * @param user
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void addUserB(User user) {

		userMapper.insertUserB(user);
		throw new RuntimeException("Nested 方法B 抛出异常");
	}
}
