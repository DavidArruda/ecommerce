package com.david.security.domain.repository;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.david.security.domain.model.User;

public class UserRepository {
	
	private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";

	@Autowired
	private DataSource dataSource;

	public User findById(int id) throws SQLException {
		User user = null;
		
		var st = dataSource.getConnection().prepareStatement(FIND_BY_ID);
		st.setInt(1, id);

		var rs = st.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
			}

		return user;
	}

}
