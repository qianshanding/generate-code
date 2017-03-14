package com.qianshanding.generate.db.entity;

import lombok.Data;

/**
 * 表字段
 * 
 * @author Fish
 * 
 */
@Data
public class TableCarray {
	private String carrayName;// 原名称
	private String carrayName_d;// 首字母大写
	private String carrayName_x;// 首字母小写
	private String carrayName_Java;//Java语法规范的命名规则
	private String carrayType;// 字段类型
	private String carrayType_d;// 大写字段类型
	private String comment;

	public TableCarray(String carrayName, String carrayNameD,
			String carrayNameX, String carrayName_Java,String carrayType,String carrayType_d,String comment) {
		super();
		this.carrayName = carrayName;
		carrayName_d = carrayNameD;
		carrayName_x = carrayNameX;
		this.carrayName_Java = carrayName_Java;
		this.carrayType = carrayType;
		this.carrayType_d = carrayType_d;
		this.comment = comment;
	}
}