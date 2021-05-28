package com.david.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.ecommerce.model.ProductHasCategory;

@Repository
public interface ProductHasCategRepository extends JpaRepository<ProductHasCategory, Long>{

}
