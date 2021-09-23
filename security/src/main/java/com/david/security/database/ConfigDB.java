package com.david.security.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDB {
	
	@Autowired
	public ConfigDB(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private final DataSource dataSource;
	
	@Bean
	public Optional<Connection> getConnection () {
		
		try {
			Connection con = dataSource.getConnection();
			return Optional.ofNullable(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

}
