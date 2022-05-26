package com.pedalclecle.lifeplan.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.pedalclecle.lifeplan.util.PropertyUtil;

@Service
public class IncomeTaxCalc implements LifePlanCalcInterface<Integer, Integer> {

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

				BigDecimal tax = income.multiply(taxRate).divide(new BigDecimal(100)).subtract(deduction);
				tax = tax.setScale(0, RoundingMode.DOWN);
				return tax.intValue();
			}
		}

		BigDecimal income = new BigDecimal(s);
		BigDecimal taxRate = new BigDecimal(incomeTaxRate[incomeTaxRate.length-1][1]);
		BigDecimal deduction = new BigDecimal(incomeTaxRate[incomeTaxRate.length-1][2]);

		BigDecimal tax = income.multiply(taxRate).divide(new BigDecimal(100)).subtract(deduction);
		return tax.setScale(0, RoundingMode.DOWN).intValue();
	}
}
