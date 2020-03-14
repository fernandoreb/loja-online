package com.farg.sales.taxes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Entity for products
 * @author fernandoreb
 *
 */
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * SKU of product
	 */
	@NotEmpty(message = "Please provide a sku")
	private String sku;
	
	/**
	 * Name of product
	 */
	@NotEmpty(message = "Please provide a name")
	private String name;
	
	/**
	 * Price of product
	 */
	@NotNull(message = "Please provide a price")
	private Float price;
	
	/**
	 * SKU of product
	 */
	@NotEmpty(message = "Please provide a description")
	private String description;
	
	/**
	 * URL of Image
	 */
	@NotEmpty(message = "Please informe a url to image of product")
	private String url;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * List of sales taxes
	 */
	@ManyToMany
	private List<SaleTax> saleTaxes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<SaleTax> getSaleTaxes() {
		return saleTaxes;
	}

	public void setSaleTaxes(List<SaleTax> saleTaxes) {
		this.saleTaxes = saleTaxes;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
		
}
