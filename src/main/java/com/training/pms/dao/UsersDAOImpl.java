package com.training.pms.dao;

import java.sql.*;
import com.training.model.Users;
import com.training.pms.utility.DBConnection;

public class UsersDAOImpl implements UsersDAO{
	
	Connection connection = DBConnection.getConnection();	
	
	public boolean register(Users user) {
		System.out.println("Creating user: " + user);
		PreparedStatement statement = null;
		int rows = 0;
		
		try {
			statement = connection.prepareStatement("insert into userAccounts values (default, ?, ?, ?");
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullName());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validate(String username, String password) {
		boolean userValid = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from login where username = ? and password = ? ");
			stat.setString(1, username);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();
			userValid = res.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userValid;
	}

}