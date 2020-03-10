package com.farg.sales.taxes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farg.sales.taxes.model.ShoppingCart;

/**
 * This class provide the crud functionalities for ShoppingCart Entity
 * @author fernandoreb
 *
 */
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

}
