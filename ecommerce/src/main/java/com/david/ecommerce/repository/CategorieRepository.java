package com.david.ecommerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.david.ecommerce.model.Category;

@Repository
@Transactional
public interface CategorieRepository extends PagingAndSortingRepository<Category, Long>{

}
