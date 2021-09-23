package com.david.partners.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.david.partners.domain.repository.PartnerRepository;

@Service
public class PartnerService implements UserDetailsService{

	private final PartnerRepository partnerRepository;
	
	@Autowired
	public PartnerService(PartnerRepository partnerRepository) {
		this.partnerRepository = partnerRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		return partnerRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}

}
