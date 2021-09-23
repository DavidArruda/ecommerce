package com.david.security.domain.resource;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.david.security.domain.model.User;
import com.david.security.domain.repository.UserRepository;

@RestController
public class UserResource {

	private final UserRepository repository;
	
	public UserResource() {
		this.repository = new UserRepository();
	}

	public ResponseEntity<User> finById(@PathVariable int id) throws SQLException {
		return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
	}

}
