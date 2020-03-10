package com.farg.sales.taxes.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class provide common utilities
 * @author fernandoreb
 *
 */
public class Utils {
	
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	/**
	 * Calculate the value percent and apply rounding. 
	 * @param value
	 * @param percent
	 * @return
	 */
	public static BigDecimal calcPercent(BigDecimal value, BigDecimal percent) {
		return value.multiply(percent).divide(ONE_HUNDRED).setScale(1,RoundingMode.HALF_UP);
	}
}
