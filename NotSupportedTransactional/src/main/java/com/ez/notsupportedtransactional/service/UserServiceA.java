package com.ez.notsupportedtransactional.service;

import com.ez.notsupportedtransactional.bean.User;
import com.ez.notsupportedtransactional.mapper.UserMapper;
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
	 * 测试：Not_Supported
	 * 父方法存在事务，子方法事务传播为Not_Supported，此时父方法会挂起。
	 *
	 * 运行结果：
	 * 父方法，子方法都正常提交
	 *
	 * @param userA
	 * @param userB
	 */
	@Override
	@Transactional
	public void addUserA(User userA, User userB) {
		userMapper.insertUserA(userA);
		System.out.println("执行A");
		userServiceB.addUserB(userB);
	}

	@Override
	public void addUserB(User user) {
		// DO NOTHING in Service A
	}
}
