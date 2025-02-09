package com.generation.game_store.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="The name is mandatory")
	@Size(max=255, message="The name must contain a maximun of 255 characters")
	private String name;
	
	@Size(max=2000, message="The description must contain a maximum of 2000 characters")
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="category", cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("category")
	private List<Product> product;
	
	public List<Product> getProduct(){
		return product;
	}
	
	public void setProduct(List<Product> product) {
		this.product = product;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
