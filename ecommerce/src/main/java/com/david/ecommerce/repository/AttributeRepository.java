package com.david.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.david.ecommerce.model.ProductAttribute;

@Repository
@Transactional
public interface AttributeRepository extends JpaRepository<ProductAttribute, String>{
	
	

}
