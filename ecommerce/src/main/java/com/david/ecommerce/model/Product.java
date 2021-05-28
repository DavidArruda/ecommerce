package com.david.ecommerce.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 120, nullable = false)
	private String name;

	@Column(length = 20, nullable = false, unique = true)
	private String sku;

	private Boolean status;

	@Column(columnDefinition = "text", unique = true, nullable = false)
	private String ean;

	@Column(length = 90)
	private String brand;

	@Column(columnDefinition = "text", nullable = false)
	private String description;

	private Integer qty;

	@Column(precision = 15, scale = 4, nullable = false)
	private BigDecimal costPrice;

	@Column(precision = 15, scale = 4, nullable = false)
	private BigDecimal price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_categorie"), nullable = false)
	private Category category;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<Image> images;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "products_has_attributes", joinColumns = {
			@JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "attribute_key") })
	Set<ProductAttribute> attributes;

	@Deprecated
	public Product() {
		// CONSTRUTOR VAZIO
	}

	public Product(Long id, String name, String sku, String ean, String brand, String description, Integer qty,
			BigDecimal costPrice, BigDecimal price, Category category, List<Image> images,
			Set<ProductAttribute> attributes) {
		super();
		this.id = id;
		this.name = name;
		this.sku = sku;
		this.ean = ean;
		this.brand = brand;
		this.description = description;
		this.qty = qty;
		this.costPrice = costPrice;
		this.price = price;
		this.category = category;
		this.images = images;
		this.attributes = attributes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Image> getImages() {
		return images;
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

	public Set<ProductAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<ProductAttribute> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
