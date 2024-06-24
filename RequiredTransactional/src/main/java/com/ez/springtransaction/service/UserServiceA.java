package com.ez.springtransaction.service;

import com.esotericsoftware.minlog.Log;
import com.ez.springtransaction.bean.User;
import com.ez.springtransaction.mapper.UserMapper;
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
	 * 可以进行多个不同类型测试：
	 * 父类异常，子类是否回滚
	 * 子类异常，父类是否回滚
	 * 父类捕捉异常，父类是否回滚
	 * 子类异常，父类捕捉，是否回滚
	 *
	 * @param userA
	 * @param userB
	 */
	// @Override
	// public void addUserA(User userA, User userB) {
	// 	userMapper.insertUserA(userA);
	// 	userServiceB.addUserB(userB);
	// }

	/**
	 * 测试父类异常，子类是否发生回滚
	 * 结果：发生回滚，A，B都没有写入成功
	 *
	 * @param userA
	 * @param userB
	 */
	@Override
	public void addUserA(User userA, User userB) {
		userMapper.insertUserA(userA);
		userServiceB.addUserB(userB);
		throw new RuntimeException("A方法抛出异常");
	}

	/**
	 * 测试，父类捕捉到异常，是否回滚
	 * 捕捉到异常，还是发生回滚，数据库没有写入
	 *
	 * @param userA
	 * @param userB
	 */
	// @Override
	// public void addUserA(User userA, User userB) {
	//
	// 	try {
	// 		userMapper.insertUserA(userA);
	// 		userServiceB.addUserB(userB);
	// 	} catch (Exception e) {
	// 		Log.info("捕捉到异常");
	// 	}
	// }


	@Override
	public void addUserB(User user) {
		// DO NOTHING in Service A
	}
}
