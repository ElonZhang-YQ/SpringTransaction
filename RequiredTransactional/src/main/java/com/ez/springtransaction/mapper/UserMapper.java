package com.ez.springtransaction.mapper;

import com.ez.springtransaction.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/24
 */
@Mapper
public interface UserMapper {

	@Insert("INSERT INTO transaction01(id, name) VALUES (#{id}, #{name})")
	void insertUserA(User user);

	@Insert("INSERT INTO transaction02(id, name) VALUES (#{id}, #{name})")
	void insertUserB(User user);
}
