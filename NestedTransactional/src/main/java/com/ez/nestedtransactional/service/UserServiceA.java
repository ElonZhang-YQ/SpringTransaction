package com.ez.nestedtransactional.service;

import com.esotericsoftware.minlog.Log;
import com.ez.nestedtransactional.bean.User;
import com.ez.nestedtransactional.mapper.UserMapper;
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
@Transactional
public class UserServiceA implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userServiceB;

	/**
	 * 事务：Nested
	 * 父方法异常回滚，子方法也回滚
	 *
	 * 结果：
	 * 父方法和子方法全部回滚
	 * java.lang.RuntimeException: Nested 方法A 抛出异常
	 *
	 * @param userA
	 * @param userB
	 */
	// @Override
	// @Transactional
	// public void addUserA(User userA, User userB) {
	// 	userMapper.insertUserA(userA);
	// 	userServiceB.addUserB(userB);
	// 	throw new RuntimeException("Nested 方法A 抛出异常");
	// }

	/**
	 * 事务：Nested
	 * 子方法抛出异常，父方法不处理，是否回滚
	 *
	 * 运行结果：
	 * 父方法和子方法全部回滚
	 * java.lang.RuntimeException: Nested 方法B 抛出异常
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
	 * 事务：Nested
	 * 子方法抛出异常，父方法捕获处理，是否回滚
	 *
	 * 运行结果：
	 * 父方法执行成功，子方法回滚
	 * INFO: Nested 方法A 捕获异常
	 *
	 * @param userA
	 * @param userB
	 */
	@Override
	@Transactional
	public void addUserA(User userA, User userB) {
		userMapper.insertUserA(userA);
		try {
			userServiceB.addUserB(userB);
		} catch (Exception e) {
			Log.info("Nested 方法A 捕获异常");
		}
	}

	@Override
	public void addUserB(User user) {
		// DO NOTHING in Service A
	}
}
