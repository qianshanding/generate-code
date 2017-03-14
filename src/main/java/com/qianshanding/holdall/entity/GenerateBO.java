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
    private boolean isCreateDal;
    /**
     * 是否生成BO
     */
    private boolean isCreateBo;
    /**
     * 是否生成Transfer
     */
    private boolean isCreateTransfer;
    /**
     * 是否生成service层
     */
    private boolean isCreateService;
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
    private String mybatisXmlPath;
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
    private String driverClassName;
    private String userName;
    private String password;
}
