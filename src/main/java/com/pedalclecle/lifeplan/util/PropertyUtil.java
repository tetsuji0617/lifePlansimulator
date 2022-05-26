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
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static String[] getIncomeTaxRate() {
		return properties.getProperty("income.tax.rate").split(",");
	}
}
