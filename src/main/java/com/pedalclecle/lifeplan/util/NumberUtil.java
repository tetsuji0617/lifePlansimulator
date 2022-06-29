package com.pedalclecle.lifeplan.util;

import java.math.BigDecimal;

/**
 * 数字変換用ユーティリティ
 * @author tetsuji0617
 *
 */
public class NumberUtil {

	/**
	 * String to Decimal
	 * @param number number format String
	 * @return BigDecimal
	 */
	public static BigDecimal stringToDecimal(String number) {
		return new BigDecimal(number);
	}

	/**
	 * int to Decimal
	 * @param number type of int
	 * @return BigDecimal
	 */
	public static BigDecimal intToDecimal(int number) {
		return new BigDecimal(number);
	}
}
