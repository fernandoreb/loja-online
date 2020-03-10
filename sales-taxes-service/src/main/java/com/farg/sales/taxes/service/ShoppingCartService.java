package com.farg.sales.taxes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farg.sales.taxes.model.ShoppingCart;
import com.farg.sales.taxes.repository.ShoppingCartRepository;

/**
 * This class prodive a service to shopping cart
 * @author fernandoreb
 *
 */
@Service
public class ShoppingCartService {
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	/**
	 * Get all carts
	 * @return
	 */
	public Iterable<ShoppingCart>getAllCarts(){
		return shoppingCartRepository.findAll();
	}
	
	/**
	 * Save a Shopping Cart
	 * @param product
	 * @return
	 */
	public ShoppingCart save(ShoppingCart shoppingCart) {
		return shoppingCartRepository.save(shoppingCart);
	}
	
	/**
	 * Find a cart by id
	 * @param id
	 * @return
	 */
	public Optional<ShoppingCart> getCartById(Integer id) {
		return shoppingCartRepository.findById(id);
	}
}
