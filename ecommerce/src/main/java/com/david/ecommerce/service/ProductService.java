package com.david.ecommerce.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.david.ecommerce.dto.ProductDTO;
import com.david.ecommerce.exception.EntityNotFoundException;
import com.david.ecommerce.exception.ProblemType;
import com.david.ecommerce.model.Image;
import com.david.ecommerce.model.Product;
import com.david.ecommerce.repository.AttributeRepository;
import com.david.ecommerce.repository.CategorieRepository;
import com.david.ecommerce.repository.ImageRepository;
import com.david.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;
	private final CategorieRepository repositoryCateg;
	private final ImageRepository repositoryImg;
	private final AttributeRepository repositoryAttribute;

	@Autowired
	public ProductService(ProductRepository repository, CategorieRepository repositoryCateg,
			ImageRepository repositoryImg, AttributeRepository attributeRepository) {
		this.repository = repository;
		this.repositoryCateg = repositoryCateg;
		this.repositoryImg = repositoryImg;
		this.repositoryAttribute = attributeRepository;
	}

	public Product save(ProductDTO product) {
		if (product.getCategories() != null && !product.getCategories().isEmpty()) {
			existsCategories(product.getCategories());
		}

		if (product.getAttributes() != null && !product.getAttributes().isEmpty()) {
			existsAttributes(product.getAttributes());
		}
		
		/*var prdt = Product.builder()
									.name(product.getName())
									.sku(product.getSku())
									.status(product.getStatus())
									.description(product.getDescription())
									.brand(product.getBrand())
									.qty(product.getQty())
									.costPrice(product.getCostPrice())
									.price(product.getPrice())
									.build();
		*/
		//prdt = repository.save(prdt);
		
		//saveImages(product.getImages(), prdt);

		return null;
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
			if (!repositoryAttribute.existsById(attr)) {
				throw new EntityNotFoundException(ProblemType.ATTRIBUTE_NOT_FOUND);
			}
		}
	}

	private void existsCategories(Set<Long> categories) {
		for (Long categ : categories) {
			if (!repositoryCateg.existsById(categ)) {
				throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND);
			}
		}
	}

	public Page<ProductDTO> find(Pageable pageRequest) {
		Page<Product> page = repository.findAll(pageRequest);
		repository.findProduct(page.stream().collect(Collectors.toList()));
		return page.map(x -> new ProductDTO());
	}

	public Product findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND));
	}

	public Product update(Product product) {
		if (!repository.existsById(product.getId())) {
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND);
		}

		return repository.save(product);
	}

	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND);
		}
		repository.deleteById(id);
	}

}
