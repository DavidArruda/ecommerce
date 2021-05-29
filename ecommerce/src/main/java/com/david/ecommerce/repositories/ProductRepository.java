package com.david.ecommerce.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.david.ecommerce.model.Product;

@Repository
@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	
	Product findByEan(String ean);
	
}
