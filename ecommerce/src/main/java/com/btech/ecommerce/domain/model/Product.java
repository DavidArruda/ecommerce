package com.btech.ecommerce.domain.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo name não pode ser nulo ou vazio")
	@Size(min = 3, max = 120, message = "Campo nome deve conter entre 3 e 120 caracteres")
	@Column(length = 120, nullable = false)
	private String name;

	@NotBlank(message = "Campo sku não pode ser nulo ou vazio")
	@Size(min = 3, max = 120, message = "Campo sku de conter entre 3 e 20 caracteres")
	@Column(length = 20, nullable = false, unique = true)
	private String sku;

	private Boolean status;

	@Column(columnDefinition = "text", unique = true, nullable = false)
	private String ean;

	@Size(min = 1, max = 90, message = "Campo brand deve conter entre 1 e 90 caracteres")
	@Column(length = 90)
	private String brand;

	@Column(columnDefinition = "text", nullable = false)
	private String description;

	@Min(value = 0, message = "Campo qty deve ser >= 0")
	@Max(value = Integer.MAX_VALUE)
	private Integer qty;

	@DecimalMin(value = "0.0", message = "Campo costPrice deve ser maior >= 0")
	@Digits(integer = 15, fraction = 4, message = " Campo costPrice fora do limite. Esperado: numeric 15,4")
	@Column(precision = 15, scale = 4, nullable = false)
	private BigDecimal costPrice;

	@DecimalMin(value = "0.0", message = "Campo price deve ser maior >= 0")
	@Digits(integer = 15, fraction = 4, message = " Campo price fora do limite. Esperado: numeric 15,4")
	@Column(precision = 15, scale = 4, nullable = false)
	private BigDecimal price;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "products_has_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Image> images;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "products_has_attributes", joinColumns = {
			@JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "attribute_key") })
	Set<ProductAttribute> attributes;

	public Product() {}
	
	public Product(Long id, String name, String sku, Boolean status,
				   String ean, String brand, String description,
				   Integer qty, BigDecimal costPrice, BigDecimal price,
			       Set<Category> categories, Set<Image> images,
				   Set<ProductAttribute> attributes) {
		super();
		this.id = id;
		this.name = name;
		this.sku = sku;
		this.status = status;
		this.ean = ean;
		this.brand = brand;
		this.description = description;
		this.qty = qty;
		this.costPrice = costPrice;
		this.price = price;
		this.categories = categories;
		this.images = images;
		this.attributes = attributes;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setProduct(this);
		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setProduct(null);
		return image;
	}

}
