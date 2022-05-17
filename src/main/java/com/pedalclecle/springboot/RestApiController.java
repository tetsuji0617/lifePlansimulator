package com.pedalclecle.springboot;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Dataset;
import bean.ResponseBean;

@RestController
public class RestApiController {
	@RequestMapping(value="/api/getSample", method = {RequestMethod.GET})
	@ResponseBody
	@CrossOrigin
	public String receiveJsonSample() throws JsonProcessingException {
		System.out.println("receiveJsonSamle()");

		ResponseBean bean = new ResponseBean();
		String[] labels = {"January", "February", "March", "April", "May", "June", "July"};

		Dataset[] datasets = new Dataset[2];
		Dataset dataset = new Dataset();
		String[] data = {"100","100","100","100","100","1000","1000"};
		String backgroundColor = "rgba(255, 99, 132, 0.5)";
		dataset.setLabel("Dataset 1");
		dataset.setData(data);
		dataset.setBackgroundColor(backgroundColor);

		datasets[0] = dataset;
		datasets[1] = dataset;

		bean.setLabels(labels);
		bean.setDatasets(datasets);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bean);


		return json;
	}
}
