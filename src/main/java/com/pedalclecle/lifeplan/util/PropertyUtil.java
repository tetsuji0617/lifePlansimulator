package com.pedalclecle.lifeplan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

public class PropertyUtil {

	private static Properties properties;

	private static String[] incomeTaxRate;

	static {
		properties = new Properties();
		try {
//			InputStream input = Application.class.getResourceAsStream("application.properties");
			File file = ResourceUtils.getFile("classpath:application.properties");
			properties.load(new FileInputStream(file));
			String[] incomeTaxRate = properties.getProperty("income.tax.rate").split(",");
			for(int i = 0; i < incomeTaxRate.length; i++) {
				System.out.println(incomeTaxRate[i]);
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static String[] getIncomeTaxRate() {
		return properties.getProperty("income.tax.rate").split(",");
	}
}
