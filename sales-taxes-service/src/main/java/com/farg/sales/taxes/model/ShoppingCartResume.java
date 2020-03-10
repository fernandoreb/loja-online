package com.farg.sales.taxes.model;

/**
 * Class for shopping cart resume
 * @author fernandoreb
 *
 */
public class ShoppingCartResume {

	/**
	 * The associated cart
	 */
	private ShoppingCart shoppingCart;
	
	/**
	 * Sum of value of all taxes
	 */
	private Float salesTaxes;

	/**
	 * Sum of all prices and taxes
	 */
	private Float total;

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Float getSalesTaxes() {
		return salesTaxes;
	}

	public void setSalesTaxes(Float salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

}
