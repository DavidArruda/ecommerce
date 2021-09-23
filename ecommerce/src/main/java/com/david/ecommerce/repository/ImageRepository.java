package com.david.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.david.ecommerce.model.Image;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {

//	@Modifying
//	@Query(nativeQuery = true, value = "insert into images (url, product_id) values(:url, :produto_id)")
//	void saveSQLNative(@Param("url") String url, @Param("product_id") long product);

}
