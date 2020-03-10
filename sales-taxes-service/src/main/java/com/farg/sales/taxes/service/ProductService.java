package com.farg.sales.taxes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farg.sales.taxes.model.Product;
import com.farg.sales.taxes.repository.ProductRepository;

/**
 * This class provide a service to products
 * @author fernandoreb
 *
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * Get all products
	 * @return
	 */
	public Iterable<Product>getAllProducts(){
		return productRepository.findAll();
	}
	
	/**
	 * Save a product
	 * @param product
	 * @return
	 */
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	/**
	 * Find a product by id
	 * @param id
	 * @return
	 */
	public Optional<Product> getProductById(Integer id) {
		return productRepository.findById(id);
	}
}
