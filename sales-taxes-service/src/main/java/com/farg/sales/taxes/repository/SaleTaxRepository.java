package com.farg.sales.taxes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farg.sales.taxes.model.SaleTax;

/**
 * This class provide the crud functionalities for SaleTax Entity
 * @author fernandoreb
 *
 */
@Repository
public interface SaleTaxRepository extends CrudRepository<SaleTax, Integer> {

}
