package com.pedalclecle.lifeplan.bean;

import lombok.Data;

/**
 * 世帯主情報
 * @author tetsuji0617
 *
 */
@Data
public class Household {

	/**
	 * 世帯主 生まれ年(西暦)
	 */
	String birthYear;

	/**
	 * 世帯主 生まれ月(1～12)
	 */
	String birthMonth;

	/**
	 * 世帯主 収入(総額、額面)
	 */
	String income;

	/**
	 * 世帯主 リタイア年齢
	 */
	String retirementAge;
}
