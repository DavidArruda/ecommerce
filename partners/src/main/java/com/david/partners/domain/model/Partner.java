package com.david.partners.domain.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Partner implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(unique = true, length = 45, nullable = false)
	private String username;

	@Column(name = "password")
	private String passWord;

	private Boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		var authorits = new ArrayList<SimpleGrantedAuthority>();
		authorits.add(new SimpleGrantedAuthority("ROLE_PARTNER"));
		
		return authorits;
	}

	@Override
	public String getPassword() {
		return this.passWord;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
