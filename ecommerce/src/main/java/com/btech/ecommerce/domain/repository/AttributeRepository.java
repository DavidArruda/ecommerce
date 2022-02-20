package com.btech.ecommerce.domain.repository;

import com.btech.ecommerce.domain.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AttributeRepository extends JpaRepository<ProductAttribute, String>{
	
	

}
