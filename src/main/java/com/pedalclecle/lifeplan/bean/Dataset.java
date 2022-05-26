package com.pedalclecle.lifeplan.bean;

import lombok.Data;

@Data
public class Dataset {

	/**
	 * グラフのタイプ.
	 */
	String type;

	/**
	 * グラフのラベル.
	 */
	String label;

	/**
	 * グラフのデータ.
	 */
	Integer data[];

	/**
	 * グラフの線の色.
	 */
	String backgroundColor;

	/**
	 * グラフの線の色_Red.
	 */
	int backgroundColorRed;

	/**
	 * グラフの線の色_Blue.
	 */
	int backgroundColorBlue;

	/**
	 * グラフの線の色_Green.
	 */
	int backgroundColorGreen;

	/**
	 * グラフの線の色_Alpha
	 */
	float backgroundColorAlpha;

}
