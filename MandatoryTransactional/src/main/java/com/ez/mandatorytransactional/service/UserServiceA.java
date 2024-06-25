package com.ez.mandatorytransactional.service;

import com.ez.mandatorytransactional.bean.User;
import com.ez.mandatorytransactional.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * 事务：Mandatory
	 * 如果当前父方法没有事务，抛出异常
	 *
	 * 运行结果：
	 * 父方法执行成功，没有开启事务。
	 * 子方法抛出异常
	 * org.springframework.transaction.IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
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
