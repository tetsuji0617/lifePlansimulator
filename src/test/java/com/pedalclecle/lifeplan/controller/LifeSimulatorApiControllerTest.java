package com.pedalclecle.lifeplan.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootTest
@AutoConfigureMockMvc
class LifeSimulatorApiControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	LifeSimulatorApiController lifeSimulatorApiController;

	@BeforeEach
	void setUp() {
		lifeSimulatorApiController= new LifeSimulatorApiController();
	}

	@Test
	void testGetHealthInsuranceDeducation() throws JsonProcessingException {
//		lifeSimulatorApiController.receiveJsonSample();
	}

}
