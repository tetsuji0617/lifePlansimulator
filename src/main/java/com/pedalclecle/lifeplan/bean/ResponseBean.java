package com.pedalclecle.lifeplan.bean;

import lombok.Data;

@Data
public class ResponseBean {

	/**
	 * グラフのラベル.
	 */
	Integer[] labels;

	/**
	 * グラフに表示するデータセット.
	 */
	Dataset[] datasets;

}
