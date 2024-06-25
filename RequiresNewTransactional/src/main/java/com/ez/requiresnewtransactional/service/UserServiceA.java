package com.ez.requiresnewtransactional.service;

import com.esotericsoftware.minlog.Log;
import com.ez.requiresnewtransactional.bean.User;
import com.ez.requiresnewtransactional.mapper.UserMapper;
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
	 * 此时验证：Requires_NEW
	 * 父类抛出异常，子类无论父类是否有事务，都开启新事物，且独立于父事物。
	 * 结果：父类回滚，子类提交成功
	 * java.lang.RuntimeException: Requires_NEW 方法A 抛出异常
	 *
	 * @param userA
	 * @param userB
	 */
	// @Override
	// @Transactional
	// public void addUserA(User userA, User userB) {
	// 	userMapper.insertUserA(userA);
	// 	userServiceB.addUserB(userB);
	// 	throw new RuntimeException("Requires_NEW 方法A 抛出异常");
	// }

	/**
	 * 此时验证：Requires_NEW
	 * 父类不抛出异常，子类无论父类是否有事务，都开启新事物，且独立
	 * 结果：父类提交成功，子类回滚
	 *
	 * ERROR：此时父类，子类全都回滚。
	 * 原因：子类异常父类没有捕捉，导致父类也抛出异常，需要在父类对子类方法进行异常捕获处理。
	 *
	 * 修改之后，运行结果：
	 * 父事务提交成功，子事务回滚
	 * INFO: Requires_NEW 方法B 捕获异常
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
			Log.info("Requires_NEW 方法B 捕获异常");
		}
	}

	@Override
	public void addUserB(User user) {
		// DO NOTHING in Service A
	}
}
