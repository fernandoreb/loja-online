package com.farg.sales.taxes.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.farg.sales.taxes.model.Product;
import com.farg.sales.taxes.model.SaleTax;
import com.farg.sales.taxes.model.ShoppingCart;
import com.farg.sales.taxes.model.ShoppingCartResume;
import com.farg.sales.taxes.service.ProductService;
import com.farg.sales.taxes.service.SaleTaxService;
import com.farg.sales.taxes.service.ShoppingCartService;

/**
 * Unit test for ShoppingCartController
 * @author fernandoreb
 *
 */
@ExtendWith(MockitoExtension.class)
public class ShoppingCartControllerTest {
	
	@InjectMocks
	ShoppingCartController shoppingCartController;
	
    @Mock
    ProductService productService;
    
    @Mock
    SaleTaxService saleTaxService;
    
    @Mock
    ShoppingCartService shoppingCartService;
    
    @Test
    public void testFindAllCarts() {
    	
    	ShoppingCart shoppingCart = new ShoppingCart();
    	List<ShoppingCart> listShoppingCart = new ArrayList<ShoppingCart>();
    	listShoppingCart.add(shoppingCart);
    	
    	when(shoppingCartService.getAllCarts()).thenReturn(listShoppingCart);
    	
    	Iterable<ShoppingCart> result = shoppingCartController.getAllShoppingCartToShoppingCart();
    	
    	assertThat(result.iterator().hasNext()).isTrue();
    	
    }
    
    @Test
    public void testCreateShoppingCart() {
    	ShoppingCart shoppingCart = new ShoppingCart();
    	
    	lenient().when(shoppingCartService.save(shoppingCart)).thenReturn(shoppingCart);
		
		String result = shoppingCartController.createShoppingCart();
		
		assertThat(result).isNotEmpty();
    }
    
    @Test void testGetResumeOfShoppingCart() {
    	
    	SaleTax saleTax = new SaleTax();
    	saleTax.setId(1);
    	saleTax.setPercent(10);
    	List<SaleTax> lSaleTax = new ArrayList<SaleTax>();
    	lSaleTax.add(saleTax);
    	
    	Product product = new Product();
    	product.setId(2);
    	product.setPrice(12.00f);
    	product.setSaleTaxes(lSaleTax);
    	List<Product> lProduct = new ArrayList<Product>();
    	lProduct.add(product);
    	
    	ShoppingCart shoppingCart = new ShoppingCart();
    	shoppingCart.setId(3);
    	shoppingCart.setProducts(lProduct);
    	
    	Optional<ShoppingCart> oShoppingCart = Optional.of(shoppingCart);
    	
    	when(shoppingCartService.getCartById(3)).thenReturn(oShoppingCart);
    	
    	ShoppingCartResume result = shoppingCartController.getResumeOfShoppingCart(3);
    	
    	assertThat(result.getTotal()).isEqualTo(13.20f);
    	assertThat(result.getSalesTaxes()).isEqualTo(1.20f);
    	
    }
    
    /*@Test
    public void testAddSaleTaxToProduct() {
    	Product product = new Product();
    	product.setId(2);
    	ShoppingCart cart = new ShoppingCart();
    	cart.setId(1);
    	
    	Optional<Product> oProduct = Optional.of(product);
    	Optional<ShoppingCart> oCart = Optional.of(cart);
    	
    	lenient().when(productService.save(product)).thenReturn(product);
    	when(shoppingCartService.getCartById(1)).thenReturn(oCart);
    	when(productService.getProductById(2)).thenReturn(oProduct);
    	//lenient().when(productService.save(product)).thenReturn(product);
    	
    	ShoppingCart result = shoppingCartController.updateShoppingCart(1, 2);
    	
    	assertThat(result.getId()).isEqualTo(2);
    }*/
    
    

}
