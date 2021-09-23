package com.david.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Builder
@Data
public class OrderItem {
	
	@Id
	private Long id;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Product product;

}
