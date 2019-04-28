package com.mmt.enterprise.service;

import org.springframework.data.domain.Page;

import com.mmt.enterprise.entity.User;

public interface UserService 
{
	User insertUser(User user);
	
	Page<User> getUsers(int pageNumber, int pageSize);
	
	Page<User> getUsersByQueries(String name, int pageNumber, int pageSize);
	
	void updateUserStatus(Long id, Integer status);
	
	User getUserByName(String name);
}
