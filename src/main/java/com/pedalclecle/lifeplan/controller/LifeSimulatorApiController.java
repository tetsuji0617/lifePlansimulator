package com.pedalclecle.lifeplan.controller;

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
import com.pedalclecle.lifeplan.service.SalaryDeductionCalc;

@RestController
public class LifeSimulatorApiController {

	private final String BACKGROUND_COLOR_ASSET = "#63FF63";
	private final String BACKGROUND_COLOR_INCOME= "#ff6363";

	@Autowired
	IncomeTaxCalc incomeTaxCalc;

	@Autowired
	SalaryDeductionCalc salaryDeductionCalc;

	@RequestMapping(value="/api/getLifePlan", method = {RequestMethod.GET})
	@ResponseBody
	@CrossOrigin
	public String receiveJsonSample() throws JsonProcessingException {

		int householdAge = 40;
		int lifeOfTheHeadOfHousehold = 100;
		int retirementAge = 60;
		int assets = 1000;

		int salaryDeducation = salaryDeductionCalc.calc(6959712);
		System.out.println(salaryDeducation);
		int incomeTax = incomeTaxCalc.calc(6959712-salaryDeducation-1548478);
		System.out.println(incomeTax);


		List<Integer> labelsList = new ArrayList<Integer>();
		List<Integer> dataList1 = new ArrayList<Integer>();
		List<Integer> dataList2 = new ArrayList<Integer>();

		for(int i = householdAge; i <= lifeOfTheHeadOfHousehold; i++) {
			labelsList.add(i);
			if(i <= retirementAge) {
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


	@RequestMapping(value="/api/getLifePlan2", method = {RequestMethod.POST})
	@ResponseBody
	@CrossOrigin
	public String receiveJson(@RequestBody InputBean input) throws JsonProcessingException {

		int householdAge = Integer.valueOf(input.getAge());
		int lifeOfTheHeadOfHousehold = 100;
		int retirementAge = 60;
		int assets = 100;

		int salaryDeducation = salaryDeductionCalc.calc(Integer.valueOf(input.getIncome())*10000);
		System.out.println(salaryDeducation);
		int incomeTax = incomeTaxCalc.calc(Integer.valueOf(input.getIncome())-salaryDeducation-1548478);
		System.out.println(incomeTax);


		List<Integer> labelsList = new ArrayList<Integer>();
		List<Integer> dataList1 = new ArrayList<Integer>();
		List<Integer> dataList2 = new ArrayList<Integer>();

		for(int i = householdAge; i <= lifeOfTheHeadOfHousehold; i++) {
			labelsList.add(i);
			if(i <= retirementAge) {
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

}
