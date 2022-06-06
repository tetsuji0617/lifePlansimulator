package com.pedalclecle.lifeplan.bean;

import lombok.Data;

@Data
public class InputBean {

	String age;

	String income;

	String expense;

	String asset;

	Household household;

	Partner partner;

}
