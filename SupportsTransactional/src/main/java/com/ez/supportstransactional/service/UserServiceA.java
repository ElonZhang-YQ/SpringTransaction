package com.ez.supportstransactional.service;

import com.ez.supportstransactional.bean.User;
import com.ez.supportstransactional.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname UserServiceA
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/24
 */
@Service
public class UserServiceA implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userServiceB;

	/**
	 * 测试：Supported
	 * 父方法有事务，子方法加入事务
	 *
	 * 执行结果：
	 * 父子方法都提交事务
	 *
	 * @param userA
	 * @param userB
	 */
	// @Override
	// @Transactional
	// public void addUserA(User userA, User userB) {
	// 	userMapper.insertUserA(userA);
	// 	userServiceB.addUserB(userB);
	// }

	/**
	 * 测试：Supported
	 * 父方法没有事务，子方法以非事务方式执行
	 *
	 * 执行结果：
	 * 父子方法正常执行（无事务）
	 *
	 * @param userA
	 * @param userB
	 */
	@Override
	public void addUserA(User userA, User userB) {
		userMapper.insertUserA(userA);
		userServiceB.addUserB(userB);
	}

	@Override
	public void addUserB(User user) {
		// DO NOTHING in Service A
	}
}
