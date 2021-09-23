package com.david.security.domain.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.david.security.domain.model.User;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";

	public Optional<User> findByid(Integer id) {
		return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, User.class, id));
	}

}
