package com.david.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 90, nullable = false)
	private String firtName;
	
	@Column(length = 90, nullable = false)
	private String lastName;

	@Column(length = 14, nullable = false)
	private String cpf;
	
	@Column(length = 120, nullable = false)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<CustomerAddresse> addresses;
	
	@Deprecated
	public Customer() {}

	public Customer(Long id, String firtName, String lastName, String cpf, String email, List<CustomerAddresse> addresses) {
		super();
		this.id = id;
		this.firtName = firtName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.email = email;
		this.addresses = addresses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirtName() {
		return firtName;
	}

	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<CustomerAddresse> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<CustomerAddresse> addresses) {
		this.addresses = addresses;
	}
}
