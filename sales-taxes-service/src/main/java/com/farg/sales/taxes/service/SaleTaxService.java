package com.farg.sales.taxes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farg.sales.taxes.model.SaleTax;
import com.farg.sales.taxes.repository.SaleTaxRepository;

/**
 * This class provide a service to sales taxes
 * @author fernandoreb
 *
 */
@Service
public class SaleTaxService {

	@Autowired
	private SaleTaxRepository saleTaxRepository;
	
	/**
	 * Find a tax by id
	 * @return
	 */
	public Optional<SaleTax> getSaleTaxById(Integer id) {
		return saleTaxRepository.findById(id);
	}
	
	/**
	 * Get all sale taxes
	 * @return
	 */
	public Iterable<SaleTax>getAllTaxes(){
		return saleTaxRepository.findAll();
	}
	
	/**
	 * Save a tax
	 * @param tax
	 * @return
	 */
	public SaleTax save(SaleTax saleTax) {
		return saleTaxRepository.save(saleTax);
	}
}
