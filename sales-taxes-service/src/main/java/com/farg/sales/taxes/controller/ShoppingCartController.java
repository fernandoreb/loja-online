package com.farg.sales.taxes.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farg.sales.taxes.model.Product;
import com.farg.sales.taxes.model.SaleTax;
import com.farg.sales.taxes.model.ShoppingCart;
import com.farg.sales.taxes.model.ShoppingCartResume;
import com.farg.sales.taxes.service.ProductService;
import com.farg.sales.taxes.service.ShoppingCartService;
import com.farg.sales.taxes.utils.Utils;

/**
 * Rest Controller to Shopping Cart features
 * @author fernandoreb
 *
 */
@RestController
@RequestMapping(path = "/cart")
public class ShoppingCartController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * Get the resume of purchased items. This method calculates taxes, total value and update 
	 * price of objects into resume before return.
	 * @param cartId
	 * @return
	 */
	@GetMapping(path = "/v1/shoppingCartResume")
	public @ResponseBody ShoppingCartResume getResumeOfShoppingCart(@RequestParam Integer cartId) {

		ShoppingCartResume shoppingCartResume = new ShoppingCartResume();

		Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.getCartById(cartId);
		ShoppingCart shoppingCart = shoppingCartOptional.get();
		shoppingCartResume.setShoppingCart(shoppingCart);

		BigDecimal total = new BigDecimal(0.0f);
		BigDecimal tax = new BigDecimal(0.0f);
		

		List<Product> products = shoppingCart.getProducts();

		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();

			List<SaleTax> saleTaxes = product.getSaleTaxes();
			BigDecimal price = new BigDecimal(product.getPrice());
			BigDecimal priceOriginal = new BigDecimal(product.getPrice());
			for (Iterator<SaleTax> iterator1 = saleTaxes.iterator(); iterator1.hasNext();) {
				SaleTax saleTax = (SaleTax) iterator1.next();
				BigDecimal percent = new BigDecimal(saleTax.getPercent());
				if (percent.floatValue() > 0.0f) {
					BigDecimal calcPercent = Utils.calcPercent(priceOriginal, percent);
					tax = tax.add(calcPercent);
					price = price.add(calcPercent);
					
				}
			}
			total = total.add(price);
			product.setPrice(price.floatValue());

		}

		shoppingCartResume.setSalesTaxes(tax.floatValue());
		shoppingCartResume.setTotal(total.floatValue());

		return shoppingCartResume;
	}

	/**
	 * Get all carts
	 * @return
	 */
	@GetMapping(path = "/v1/shoppingCart")
	public @ResponseBody Iterable<ShoppingCart> getAllShoppingCartToShoppingCart() {
		return shoppingCartService.getAllCarts();
	}

	/**
	 * Creates a new Cart
	 * @return
	 */
	@PostMapping(path = "/v1/shoppingCart")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String createShoppingCart() {

		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCartService.save(shoppingCart);

		return "" + shoppingCart.getId();
	}

	/**
	 * Add products into a Cart
	 * @param cartId
	 * @param productId
	 * @return
	 */
	@PutMapping(path = "/v1/shoppingCart")
	@ResponseStatus(HttpStatus.CREATED)
	public ShoppingCart updateShoppingCart(@RequestParam Integer cartId, @RequestParam Integer productId) {

		Optional<ShoppingCart> shoppingCart = shoppingCartService.getCartById(cartId);
		Optional<Product> product = productService.getProductById(productId);

		shoppingCart.get().getProducts().add(product.get());

		return shoppingCartService.save(shoppingCart.get());

	}

}
