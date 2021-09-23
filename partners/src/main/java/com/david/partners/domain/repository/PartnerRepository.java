package com.david.partners.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.partners.domain.model.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Short>{

	Optional<Partner> findByUsername(String username);
}
