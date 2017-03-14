package com.qianshanding.holdall.db.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by fish on 2017/3/14.
 */
@Data
public class DataBase {
    /**
     * 大写表名
     */
    private List<String> tableList;

    /**
     * 小写表名
     */
    private List<String> tableMlList;

    /**
     * do包名
     */
    private String doPackage;

    /**
     * mybatis xml路径
     */
    private String mybatisXmlPath;
}
