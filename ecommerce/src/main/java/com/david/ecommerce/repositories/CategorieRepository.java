package com.david.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.david.ecommerce.model.Category;

@Repository
@Transactional
public interface CategorieRepository extends JpaRepository<Category, Long>{

}
