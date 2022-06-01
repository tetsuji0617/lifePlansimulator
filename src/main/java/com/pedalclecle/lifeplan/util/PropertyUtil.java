package com.pedalclecle.lifeplan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

public class PropertyUtil {

	private static Properties properties;

	static {
		properties = new Properties();
		try {
			File file = ResourceUtils.getFile("classpath:application.properties");
			File file2 = ResourceUtils.getFile("classpath:lifeplanapp.properties");
			properties.load(new FileInputStream(file));
			properties.load(new FileInputStream(file2));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static String[] getIncomeTaxRate() {
		return properties.getProperty("income.tax.rate").split(",");
	}

	public static String[] getSalaryDeducationRate() {
		return properties.getProperty("salary.deducation.rate").split(",");
	}

	public static String getHealthInsuranceRate() {
		return properties.getProperty("health.insurance.rate");
	}

	public static String getWelfarePensionRate() {
		return properties.getProperty("welfare.pension.rate");
	}
}
