package com.ez.notsupportedtransactional.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @Classname User
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	private Integer id;
	private String name;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(name, user.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name);
	}
}
