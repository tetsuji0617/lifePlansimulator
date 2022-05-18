package com.pedalclecle.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Dataset2;
import bean.ResponseBean2;

@RestController
public class LifeSimulatorApiController {

	@RequestMapping(value="/api/getLifePlan", method = {RequestMethod.GET})
	@ResponseBody
	@CrossOrigin
	public String receiveJsonSample() throws JsonProcessingException {

		int householdAge = 40;
		int lifeOfTheHeadOfHousehold = 100;
		int retirementAge = 60;
		int assets = 1000;

		System.out.println("receiveJsonSamle()");

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

		ResponseBean2 bean = new ResponseBean2();
		Integer[] labels = labelsList.toArray(new Integer[labelsList.size()]);

		Dataset2[] datasets = new Dataset2[2];
		Dataset2 dataset = new Dataset2();
		Integer[] data = dataList1.toArray(new Integer[dataList1.size()]);
		String backgroundColor = "rgba(255, 99, 132, 0.5)";
		dataset.setLabel("Dataset 1");
		dataset.setData(data);
		dataset.setBackgroundColor(backgroundColor);
		dataset.setType("line");

		datasets[0] = dataset;

		Dataset2[] datasets2 = new Dataset2[2];
		Dataset2 dataset2 = new Dataset2();
		Integer[] data2 = dataList2.toArray(new Integer[dataList2.size()]);
		String backgroundColor2 = "rgba(255, 99, 132, 0.5)";
		dataset2.setLabel("Dataset 2");
		dataset2.setData(data2);
		dataset2.setBackgroundColor(backgroundColor2);
		dataset2.setType("bar");

		datasets[1] = dataset2;

		bean.setLabels(labels);
		bean.setDatasets(datasets);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bean);


		return json;
	}
}
