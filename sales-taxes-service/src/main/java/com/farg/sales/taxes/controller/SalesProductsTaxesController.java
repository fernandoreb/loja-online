package com.farg.sales.taxes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farg.sales.taxes.model.Product;
import com.farg.sales.taxes.model.SaleTax;
import com.farg.sales.taxes.service.ProductService;
import com.farg.sales.taxes.service.SaleTaxService;

/**
 * Rest Controller to Sales Taxes and Products
 * @author fernandoreb
 *
 */
@RestController
@RequestMapping(path = "/sales")
public class SalesProductsTaxesController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SaleTaxService saleTaxService;

	/**
	 * Get all products
	 * @return list of Products
	 */
	@GetMapping(path = "/v1/product")
	public @ResponseBody Iterable<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	/**
	 * Return a specified product
	 * @param productId
	 * @return
	 */
	@GetMapping(path = "/v1/product/{productId}")
	public @ResponseBody Product getProductsById(@PathVariable Integer productId) {
		Optional<Product> productOp = productService.getProductById(productId);
		
		return productOp.get();
	}

	/**
	 * Adds a new product
	 * @param name
	 * @param price
	 * @return
	 */
	@PostMapping(path = "/v1/product")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String addNewProduct(@RequestParam String name, @RequestParam Float price) {

		Product product = new Product();
		product.setName(name);
		product.setPrice(price);

		productService.save(product);
		return ""+product.getId();
	}
	
	/**
	 * Add a tax into a product
	 * @param salesTaxId
	 * @param productId
	 * @return
	 */
	@PutMapping(path = "/v1/product")
	@ResponseStatus(HttpStatus.CREATED)
	public Product addSaleTaxToProduct(@RequestParam Integer salesTaxId, @RequestParam Integer productId) {
		
		Optional<SaleTax> saleTaxOp = saleTaxService.getSaleTaxById(salesTaxId);
		Optional<Product> productOp = productService.getProductById(productId);
	
		Product product = productOp.get();
		
		product.getSaleTaxes().add(saleTaxOp.get());
		return productService.save(product);
	}
	
	/**
	 * Get all sales taxes
	 * @return
	 */
	@GetMapping(path="/v1/tax")
	public @ResponseBody Iterable<SaleTax>getAllTaxes(){
		return saleTaxService.getAllTaxes();
	}
	
	/**
	 * Add a new tax
	 * @param label
	 * @param percent
	 * @param description
	 * @return
	 */
	@PostMapping(path = "/v1/tax")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String addNewTax(@RequestParam String label, @RequestParam Integer percent, @RequestParam String description) {

		SaleTax saleTax = new SaleTax();
		saleTax.setLabel(label);
		saleTax.setPercent(percent);
		saleTax.setDescription(description);
		saleTaxService.save(saleTax);
		
		return ""+saleTax.getId();
	}

}
