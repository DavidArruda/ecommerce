package com.david.ecommerce.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DecimalMin(value = "0.0", message = "Campo totalOrdered deve ser maior >= 0")
	@Digits(integer = 15, fraction = 4, message = " Campo totalOrdered fora do limite. Esperado: numeric 15,4")
	@Column(precision = 15, scale = 4, nullable = false)
	private BigDecimal totalOrdered;

	private OffsetDateTime placedAt;

	private OffsetDateTime updatedAt;

	@ManyToOne(optional = false)
	private Customer customer;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;
	
	@Deprecated
	public Order() {}

	public Order(Long id, BigDecimal totalOrdered, OffsetDateTime placedAt, OffsetDateTime updatedAt,
			Customer customer, List<OrderItem> items) {
		super();
		this.id = id;
		this.totalOrdered = totalOrdered;
		this.placedAt = placedAt;
		this.updatedAt = updatedAt;
		this.customer = customer;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalOrdered() {
		return totalOrdered;
	}

	public void setTotalOrdered(BigDecimal totalOrdered) {
		this.totalOrdered = totalOrdered;
	}

	public OffsetDateTime getPlacedAt() {
		return placedAt;
	}

	public void setPlacedAt(OffsetDateTime placedAt) {
		this.placedAt = placedAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
