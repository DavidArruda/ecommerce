package com.btech.ecommerce.domain.service.impl;

import com.btech.ecommerce.domain.service.ProductService;
import com.btech.ecommerce.domain.model.dto.ProductDTO;
import com.btech.ecommerce.domain.exception.EntityNotFoundException;
import com.btech.ecommerce.domain.model.Image;
import com.btech.ecommerce.domain.model.Product;
import com.btech.ecommerce.domain.repository.AttributeRepository;
import com.btech.ecommerce.domain.repository.ImageRepository;
import com.btech.ecommerce.domain.repository.ProductRepository;
import com.btech.ecommerce.domain.exception.ProblemType;
import com.btech.ecommerce.domain.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;
	private final CategoryService categoryService;
	private final ImageRepository repositoryImg;
	private final AttributeRepository repositoryAttribute;

	public ProductServiceImpl(ProductRepository repository, CategoryService categoryService,
							  ImageRepository repositoryImg, AttributeRepository attributeRepository) {
		this.repository = repository;
		this.categoryService = categoryService;
		this.repositoryImg = repositoryImg;
		this.repositoryAttribute = attributeRepository;
	}

	@Override
	public Product save(ProductDTO dto) {
		categoryService.exists(dto.getCategories());

		existsAttributes(dto.getAttributes());

		Product product = new Product(null,
								   	  dto.getName(),
				                   	  dto.getSku(),
				                      dto.getStatus(),
				                      dto.getEan(),
				                      dto.getBrand(),
				                      dto.getDescription(),
								      dto.getQty(),
				                      dto.getCostPrice(),
				                      dto.getPrice(),
				            null,
				               null,
				             null);

		//prdt = repository.save(prdt);
		
		//saveImages(product.getImages(), prdt);

		return product;
	}

	private void saveImages(Set<String> imgs, Product product) {
		if (imgs != null && !imgs.isEmpty()) {
			for (String img : imgs) {
				repositoryImg.save(new Image(null, img, product));
			}
		}
	}

	private void existsAttributes(Set<String> attributes) {
		for (String attr : attributes) {
			if (!repositoryAttribute.existsById(attr))
				throw new EntityNotFoundException(ProblemType.ATTRIBUTE_NOT_FOUND);
		}
	}

	@Override
	public Page<ProductDTO> find(Pageable pageRequest) {
		Page<Product> page = repository.findAll(pageRequest);
		repository.findProduct(page.stream().collect(Collectors.toList()));
		return page.map(x -> new ProductDTO());
	}

	@Override
	public Product findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND));
	}

	@Override
	public Product update(Product product) {
		if (!repository.existsById(product.getId()))
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND);

		return repository.save(product);
	}

	@Override
	public void delete(Long id) {
		if (!repository.existsById(id))
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND);

		repository.deleteById(id);
	}

}
