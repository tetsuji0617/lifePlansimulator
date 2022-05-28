package com.pedalclecle.lifeplan.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.pedalclecle.lifeplan.util.PropertyUtil;

@Service
public class SalaryDeductionCalc implements LifePlanCalcInterface<Integer, Integer>{

	@Override
	public Integer calc(Integer s) {

		String[] salaryDeducationRateArray = PropertyUtil.getSalaryDeducationRate();
		String[][] salaryDeducationRate = new String[salaryDeducationRateArray.length][3];

		for (int i = 0; i < salaryDeducationRateArray.length; i++) {
			salaryDeducationRate[i] = salaryDeducationRateArray[i].split("-");
		}

		for (int i = 0; i < salaryDeducationRate.length - 1; i++) {
			if (s <= Integer.parseInt(salaryDeducationRate[i + 1][0])) {
				BigDecimal income = new BigDecimal(s);
				BigDecimal taxRate = new BigDecimal(salaryDeducationRate[i][1]);
				BigDecimal deduction;
				if(!salaryDeducationRate[i][2].startsWith("+")) {
					deduction = new BigDecimal(salaryDeducationRate[i][2]).multiply(new BigDecimal("-1"));
				} else {
					deduction = new BigDecimal(salaryDeducationRate[i][2].substring(1));
				}
				BigDecimal tax = income.multiply(taxRate).divide(new BigDecimal(100)).add(deduction);
				tax = tax.setScale(0, RoundingMode.DOWN);
				return tax.intValue();
			}
		}

		BigDecimal income = new BigDecimal(s);
		BigDecimal taxRate = new BigDecimal(salaryDeducationRate[salaryDeducationRate.length-1][1]);
		BigDecimal deduction = new BigDecimal(salaryDeducationRate[salaryDeducationRate.length-1][2]);

		BigDecimal tax = income.multiply(taxRate).divide(new BigDecimal(100)).add(deduction);
		return tax.setScale(0, RoundingMode.DOWN).intValue();
	}
}
