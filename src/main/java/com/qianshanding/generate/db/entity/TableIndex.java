package com.qianshanding.generate.db.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 表的索引
 * 
 * @author Fish
 * 
 */
@Data
public class TableIndex {
	private String indexName; // 索引名称
	private List<String> carrayNames;// 原关联字段
	private List<String> carrayNames_d;// 大写关联字段
	private List<String> carrayNames_x;// 小写关联字段
	private List<Map<String, String>> carrayNameMaps;// 原字段+小写字段
	private String stringCarrayNames1;// 直接拼接大写字段
	private String stringCarrayNames2;// ","拼接大写字段
	private String stringCarrayNames3;// 类型+ ","拼接大写字段
	private String stringCarrayNames4;// ","拼接小写字段
	private String stringCarrayNames5;// sqlMap中sql用的 原字段-小写字段
	private String stringCarrayNames6;//Java属性规范
	private boolean unique; // 是否唯一索引

	public TableIndex(String indexName, List<String> carrayNames,
			List<String> carrayNamesD, List<String> carrayNamesX,
			List<Map<String, String>> carrayNameMaps,
			String stringCarrayNames1, String stringCarrayNames2,
			String stringCarrayNames3, String stringCarrayNames4,
			String stringCarrayNames5,String stringCarrayNames6, boolean unique) {
		super();
		this.indexName = indexName;
		this.carrayNames = carrayNames;
		carrayNames_d = carrayNamesD;
		carrayNames_x = carrayNamesX;
		this.carrayNameMaps = carrayNameMaps;
		this.stringCarrayNames1 = stringCarrayNames1;
		this.stringCarrayNames2 = stringCarrayNames2;
		this.stringCarrayNames3 = stringCarrayNames3;
		this.stringCarrayNames4 = stringCarrayNames4;
		this.stringCarrayNames5 = stringCarrayNames5;
		this.stringCarrayNames6 = stringCarrayNames6;
		this.unique = unique;
	}
}
