package com.ez.nevertransactional.service;

import com.ez.nevertransactional.bean.User;
import com.ez.nevertransactional.mapper.UserMapper;
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
	 * 测试：Never
	 * 父方法存在事务，子方法事务NEVER。
	 * 子方法抛出异常，父方法提交还是回滚？
	 *
	 * 运行结果：
	 * 父方法事务回滚，子方法事务回滚
	 * 子方法抛出异常
	 * org.springframework.transaction.IllegalTransactionStateException: Existing transaction found for transaction marked with propagation 'never'
	 *
	 * @param userA
	 * @param userB
	 */
	@Override
	@Transactional
	public void addUserA(User userA, User userB) {
		userMapper.insertUserA(userA);
		userServiceB.addUserB(userB);
	}

	@Override
	public void addUserB(User user) {
		// DO NOTHING in Service A
	}
}
