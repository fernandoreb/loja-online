package com.farg.sales.taxes.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.farg.sales.taxes.model.Product;
import com.farg.sales.taxes.model.SaleTax;
import com.farg.sales.taxes.service.ProductService;
import com.farg.sales.taxes.service.SaleTaxService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.lenient;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for SalesProductsTaxesController
 * @author fernandoreb
 *
 */
@ExtendWith(MockitoExtension.class)
public class SalesProductsTaxesControllerTest{
	
	@InjectMocks
	SalesProductsTaxesController salesProductsTaxesController;
     
    @Mock
    ProductService productService;
    
    @Mock
    SaleTaxService saleTaxService;
     
    @Test
    public void testFindAllProducts() {
    	Product product = new Product();
    	List<Product> listProduct = new ArrayList<Product>();
    	listProduct.add(product);
    	
    	when(productService.getAllProducts()).thenReturn(listProduct);
    	
    	Iterable<Product> result = salesProductsTaxesController.getAllProducts();
    	
    	assertThat(result.iterator().hasNext()).isTrue();
    }
    
    @Test
    public void testFindProductById() {
    	Product product = new Product();
    	product.setId(1);
    	
    	Optional<Product> oProduct = Optional.of(product);
    	
    	when(productService.getProductById(1)).thenReturn(oProduct);
    	
    	Product result = salesProductsTaxesController.getProductsById(1);
    	
    	assertThat(result.getId()).isEqualTo(1);
    }
    
    @Test
    public void testAddProduct() {
    	Product product = new Product();
    	
    	lenient().when(productService.save(product)).thenReturn(product);
		
		String result = salesProductsTaxesController.addNewProduct("Product 01", 12.23f);
		
		assertThat(result).isNotEmpty();
    }
    
    @Test
    public void getAllTaxes() {
    	SaleTax saleTax = new SaleTax();
    	List<SaleTax> lSaleTax = new ArrayList<SaleTax>();
    	lSaleTax.add(saleTax);
    	
    	when(saleTaxService.getAllTaxes()).thenReturn(lSaleTax);
    	
    	Iterable<SaleTax> result = saleTaxService.getAllTaxes();
  
    	assertThat(result.iterator().hasNext()).isTrue();
    }
    
    @Test
    public void testAddSaleTax() {
    	SaleTax saleTax = new SaleTax();
    	
    	lenient().when(saleTaxService.save(saleTax)).thenReturn(saleTax);
		
		String result = salesProductsTaxesController.addNewTax("Tax 01", 5," Desc Tax 01");
		
		assertThat(result).isNotEmpty();
    }
    
    /*@Test
    public void testAddSaleTaxToProduct() {
    	Product product = new Product();
    	product.setId(2);
    	SaleTax saleTax = new SaleTax();
    	saleTax.setId(1);
    	
    	Optional<Product> oProduct = Optional.of(product);
    	Optional<SaleTax> oSaleTax = Optional.of(saleTax);
    	
    	when(saleTaxService.getSaleTaxById(1)).thenReturn(oSaleTax);
    	when(productService.getProductById(2)).thenReturn(oProduct);
    	//lenient().when(productService.save(product)).thenReturn(product);
    	
    	Product result = salesProductsTaxesController.addSaleTaxToProduct(1, 2);
    	
    	assertThat(result.getId()).isEqualTo(2);
    }*/
    
}
