package com.btech.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.btech.ecommerce.domain.model.Product;

@Repository
@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	@Query("SELECT obj FROM Product obj JOIN FETCH obj.categories" + " JOIN FETCH obj.attributes" + " JOIN FETCH obj.images" + " WHERE obj IN :products")
	List<Product> findProduct(List<Product> products);

	Product findByEan(String ean);

}