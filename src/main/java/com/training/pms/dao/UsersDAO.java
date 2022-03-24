package com.training.pms.dao;

import com.training.model.Users;

public interface UsersDAO{
	public boolean register(Users user);
	public boolean validate(String username, String password);
}
