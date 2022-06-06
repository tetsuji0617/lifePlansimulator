package com.pedalclecle.lifeplan.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedalclecle.lifeplan.bean.Dataset;
import com.pedalclecle.lifeplan.bean.InputBean;
import com.pedalclecle.lifeplan.bean.ResponseBean;
import com.pedalclecle.lifeplan.service.IncomeTaxCalc;
import com.pedalclecle.lifeplan.service.LifePlanCalcInterface;
import com.pedalclecle.lifeplan.service.SalaryDeductionCalc;
import com.pedalclecle.lifeplan.util.NumberUtil;
import com.pedalclecle.lifeplan.util.PropertyUtil;

@RestController
public class LifeSimulatorApiController {

	private final String BACKGROUND_COLOR_ASSET = "#63FF63";
	private final String BACKGROUND_COLOR_INCOME = "#ff6363";

	@Autowired
	IncomeTaxCalc incomeTaxCalc;

	@Autowired
	SalaryDeductionCalc salaryDeductionCalc;

	@Autowired
	List<LifePlanCalcInterface<Integer, Integer>> lifePlanCalcList;

	@RequestMapping(value = "/api/getLifePlan", method = { RequestMethod.GET })
	@ResponseBody
	@CrossOrigin
	public String receiveJsonSample() throws JsonProcessingException {



		int householdAge = 40;
		int lifeOfTheHeadOfHousehold = 100;
		int retirementAge = 60;
		int assets = 1000;

		int salaryDeducation = salaryDeductionCalc.calc(6959712);
		System.out.println(salaryDeducation);
		int incomeTax = incomeTaxCalc.calc(6959712 - 	salaryDeducation - 1548478);
		System.out.println(incomeTax);

		List<Integer> labelsList = new ArrayList<Integer>();
		List<Integer> dataList1 = new ArrayList<Integer>();
		List<Integer> dataList2 = new ArrayList<Integer>();

		for (int i = householdAge; i <= lifeOfTheHeadOfHousehold; i++) {
			labelsList.add(i);
			if (i <= retirementAge) {
				assets = assets + 100;
				dataList2.add(100);
			} else {
				assets = assets - 120;
				dataList2.add(-120);
			}
			dataList1.add(assets);
		}

		ResponseBean bean = new ResponseBean();
		Integer[] labels = labelsList.toArray(new Integer[labelsList.size()]);

		Dataset[] datasets = new Dataset[2];
		Dataset dataset = new Dataset();
		Integer[] data = dataList1.toArray(new Integer[dataList1.size()]);
		dataset.setLabel("Dataset 1");
		dataset.setData(data);
		dataset.setBackgroundColor(BACKGROUND_COLOR_ASSET);
		dataset.setType("line");

		datasets[0] = dataset;

		Dataset[] datasets2 = new Dataset[2];
		Dataset dataset2 = new Dataset();
		Integer[] data2 = dataList2.toArray(new Integer[dataList2.size()]);
		dataset2.setLabel("Dataset 2");
		dataset2.setData(data2);
		dataset2.setBackgroundColor(BACKGROUND_COLOR_INCOME);
		dataset2.setType("bar");

		datasets[1] = dataset2;

		bean.setLabels(labels);
		bean.setDatasets(datasets);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bean);

		return json;
	}

	@RequestMapping(value = "/api/getLifePlan2", method = { RequestMethod.POST })
	@ResponseBody
	@CrossOrigin
	public String receiveJson(@RequestBody InputBean input) throws JsonProcessingException {

		int householdAge = Integer.valueOf(input.getAge());
		int lifeOfTheHeadOfHousehold = 100;
		int retirementAge = 60;
		int assets = Integer.valueOf(input.getAsset());
		BigDecimal salary = new BigDecimal(input.getIncome()).multiply(new BigDecimal(10000));
		BigDecimal expensesPerYear = new BigDecimal(input.getExpense()).multiply(new BigDecimal(12)).multiply(new BigDecimal(10000));

		BigDecimal healthInsurance = this.getHealthInsuranceDeducation(salary, new BigDecimal(PropertyUtil.getHealthInsuranceRate()));
		BigDecimal weelfarePension = this.getHealthInsuranceDeducation(salary, new BigDecimal(PropertyUtil.getWelfarePensionRate()));

		System.out.println("health insurance:" + healthInsurance);
		System.out.println("welfare Pension :" + weelfarePension);

		int salaryDeducation = salaryDeductionCalc.calc(salary.intValue());
		System.out.println("Salary Deducation; " + salaryDeducation);
		int incomeTax = incomeTaxCalc.calc(salary.subtract(new BigDecimal(salaryDeducation)).subtract(weelfarePension).subtract(healthInsurance).subtract(new BigDecimal(480000)).intValue());
		System.out.println("income Tax:" + incomeTax);

		BigDecimal tedori = salary	.subtract(healthInsurance).subtract(weelfarePension).subtract(new BigDecimal(incomeTax));
		System.out.println("tedori:" + tedori);
		List<Integer> labelsList = new ArrayList<Integer>();
		List<Integer> dataList1 = new ArrayList<Integer>();
		List<Integer> dataList2 = new ArrayList<Integer>();

		for (int i = householdAge; i <= lifeOfTheHeadOfHousehold; i++) {
			labelsList.add(i);
			if (i <= retirementAge) {
				assets = assets + tedori.subtract(expensesPerYear).divide(new BigDecimal(10000)).intValue();
				dataList2.add(100);
			} else {
				assets = assets - 120;
				dataList2.add(-120);
			}
			dataList1.add(assets);
		}

		ResponseBean bean = new ResponseBean();
		Integer[] labels = labelsList.toArray(new Integer[labelsList.size()]);

		Dataset[] datasets = new Dataset[2];
		Dataset dataset = new Dataset();
		Integer[] data = dataList1.toArray(new Integer[dataList1.size()]);
		dataset.setLabel("Asset");
		dataset.setData(data);
		dataset.setBackgroundColor(BACKGROUND_COLOR_ASSET);
		dataset.setType("line");
//		dataset.setYAxisID("yAxisSales");

		datasets[0] = dataset;

		Dataset[] datasets2 = new Dataset[2];
		Dataset dataset2 = new Dataset();
		Integer[] data2 = dataList2.toArray(new Integer[dataList2.size()]);
		dataset2.setLabel("Income/Expense");
		dataset2.setData(data2);
		dataset2.setBackgroundColor(BACKGROUND_COLOR_INCOME);
		dataset2.setType("bar");
//		dataset2.setYAxisID("yAxisPercentage");

		datasets[1] = dataset2;

		bean.setLabels(labels);
		bean.setDatasets(datasets);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bean);

		return json;
	}

	BigDecimal getHealthInsuranceDeducation(BigDecimal salary, BigDecimal healthInsuranceRate) {
		return salary.multiply(healthInsuranceRate).divide(NumberUtil.stringToDecimal(2))
				.divide(NumberUtil.stringToDecimal(100));
	}

	BigDecimal getHealthInsuranceDeducation(int salary, BigDecimal healthInsuranceRate) {
		return getHealthInsuranceDeducation(new BigDecimal(salary), healthInsuranceRate);
	}

	BigDecimal getHealthInsuranceDeducation(int salary, int healthInsuranceRate) {
		return getHealthInsuranceDeducation(salary, new BigDecimal(healthInsuranceRate));
	}
}
