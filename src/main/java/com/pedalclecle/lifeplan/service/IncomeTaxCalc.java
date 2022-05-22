package com.pedalclecle.lifeplan.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IncomeTaxCalc implements LifePlanCalcInterface<Integer, Integer> {

	@Value("${income.tax.rate}")
	private String[] incomeTaxRate;

	@Override
	public Integer calc(Integer s) {

		for (int i = 0; i < incomeTaxRate.length; i++) {
			System.out.println(incomeTaxRate[i]);
		}
		return null;
	}

}
