package com.qianshanding.holdall.db.entity;

import lombok.Data;

/**
 * 表的主外键关系
 *
 * @author fish
 */
@Data
public class TableBind {
    private String keyName;// 键名称
    private String keyType;// 键类型
    private String tableName_d;// 大写表名称
    private String tableName_x;// 小写表名称
    private String carrayName_d;// 大写字段名称
    private String carrayName_x;// 小写字段名称

    public TableBind(String keyName, String keyType, String tableNameD,
                     String tableNameX, String carrayNameD, String carrayNameX) {
        super();
        this.keyName = keyName;
        this.keyType = keyType;
        tableName_d = tableNameD;
        tableName_x = tableNameX;
        carrayName_d = carrayNameD;
        carrayName_x = carrayNameX;
    }
}
