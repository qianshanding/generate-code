package com.qianshanding.holdall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fish on 2017/3/5.
 */
@Data
public class GenerateBO implements Serializable {
    /**
     * 是否生成Dal
     */
    private boolean createDal = true;
    /**
     * 是否生成BO
     */
    private boolean createBo;
    /**
     * 是否生成Transfer
     */
    private boolean createTransfer;
    /**
     * 是否生成service层
     */
    private boolean createService;
    /**
     * DO包路径
     */
    private String doPackage;
    /**
     * dao包路径
     */
    private String daoPackage;
    /**
     * mybatis mapper文件路径
     */
    private String mybatisXmlPath = "sqlmap";
    /**
     * bo包路径
     */
    private String boPackage;
    /**
     * transfer包路径
     */
    private String transferPackage;
    /**
     * service包路径
     */
    private String servicePackage;
    /**
     * 要生成的表
     */
    private List<String> tableNames;
    /**
     * 生成者名称
     */
    private String author = "robot";

    /**
     * mysql连接信息
     */
    private String url;
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String passWord = "root";

    private String databaseIp = "127.0.0.1";
    private String database;
    private Integer port = 3306;
}
