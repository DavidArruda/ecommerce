package com.btech.ecommerce.domain.repository;

import com.btech.ecommerce.domain.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends PagingAndSortingRepository<Category, Category>{

}
