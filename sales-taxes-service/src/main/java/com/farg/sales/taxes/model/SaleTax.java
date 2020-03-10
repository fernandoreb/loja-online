package com.farg.sales.taxes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * Entity for sales taxes
 * @author fernandoreb
 *
 */
@Entity
public class SaleTax {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Percent of tax
	 */
	private Integer percent;
	
	/**
	 * Label of tax. 
	 * example: Import duty tax
	 */
	@NotEmpty(message = "Please provide a label")
	private String label;
	
	/**
	 * Full description of tax
	 * example: Import duty is an additional sales tax applicable on all 
	 * imported goods at a rate of 5%, with no exemptions.
	 */
	@NotEmpty(message = "Please provide a description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
