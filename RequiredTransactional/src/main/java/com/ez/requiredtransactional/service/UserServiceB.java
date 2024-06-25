package com.ez.requiredtransactional.service;

import com.ez.requiredtransactional.bean.User;
import com.ez.requiredtransactional.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	/**
	 * 此时为了测试required事务的情况
	 * A调用B，此时B抛出异常。
	 * 子类抛出异常，事务回滚。整个事务全部回滚（父类也回滚）
	 * A，B插入操作失败，数据库无数据
	 * @param user
	 */
	// @Override
	// public void addUserB(User user) {
	//
	// 	userMapper.insertUserB(user);
	// 	throw new RuntimeException("B方法抛出异常");
	// }

	/**
	 * 测试：不抛出异常，父类抛出异常是否能回滚
	 * @param user
	 */
	@Override
	public void addUserB(User user) {

		userMapper.insertUserB(user);
	}
}
