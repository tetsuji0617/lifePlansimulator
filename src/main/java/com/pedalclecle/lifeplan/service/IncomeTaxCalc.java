package com.pedalclecle.lifeplan.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.pedalclecle.lifeplan.util.PropertyUtil;

/**
 * 所得税計算
 *
 * <pre>
 * 所得税計算用モジュール
 * </pre>
 * @author tetsuji0617
 *
 */
@Service
public class IncomeTaxCalc implements LifePlanCalcInterface<Integer, Integer> {

	/**
	 * 所得税計算
	 *
	 * @param s 基礎控除後の所得
	 */
	@Override
	public Integer calc(Integer s) {

		String[] incomeTaxRateArray = PropertyUtil.getIncomeTaxRate();
		String[][] incomeTaxRate = new String[incomeTaxRateArray.length][3];

		for (int i = 0; i < incomeTaxRateArray.length; i++) {
			incomeTaxRate[i] = incomeTaxRateArray[i].split("-");
		}

		for (int i = 0; i < incomeTaxRate.length - 1; i++) {
			if (s <= Integer.parseInt(incomeTaxRate[i + 1][0])) {
				BigDecimal income = new BigDecimal(s);
				BigDecimal taxRate = new BigDecimal(incomeTaxRate[i][1]);
				BigDecimal deduction = new BigDecimal(incomeTaxRate[i][2]);

				BigDecimal tax = (income.subtract(income.remainder(new BigDecimal(1000)))).multiply(taxRate).divide(new BigDecimal(100)).subtract(deduction);
				tax = tax.setScale(0, RoundingMode.DOWN);
				return tax.intValue();
			}
		}

		BigDecimal income = new BigDecimal(s);
		BigDecimal taxRate = new BigDecimal(incomeTaxRate[incomeTaxRate.length-1][1]);
		BigDecimal deduction = new BigDecimal(incomeTaxRate[incomeTaxRate.length-1][2]);

		BigDecimal tax = (income.subtract(income.remainder(new BigDecimal(1000)))).multiply(taxRate).divide(new BigDecimal(100)).subtract(deduction);
		return tax.setScale(0, RoundingMode.DOWN).intValue();
	}
}
