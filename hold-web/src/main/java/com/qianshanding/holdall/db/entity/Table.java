package com.qianshanding.holdall.db.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Table {
    private String doPackage;                                               // DO包名称
    private String daoPackage;                                              // dao包名称
    private String boPackage;                                               // BO包名称
    private String transferPackage;                                         // transfer包名称
    private String prefix = "#{";                                           // myibatis变量前缀

    private String className;                                               // 原表名称
    private String className_d;                                             // 大写表名称
    private String className_x;                                             // 小写表名称
    private String className_ml;                                            // 小写表名称
    private String className_Java;
    private List<TableCarray> tableCarrays;                                            // 表字段
    private List<TableIndex> tableIndexs;                                             // 表索引
    private List<TableBind> tableBinds;                                              // 表主外键

    private Set<String> importPojos;                                             // 需要导入的POJO

    private String stringCarrayNames1;                                      // ","拼接大写字段
    private String stringCarrayNames2;                                      // int id ,String userCord ,..
    private String stringCarrayNames3;                                      // ","拼接原字段
    private String stringCarrayNames4;                                      // ${字段}
    private String stringCarrayNames5;                                      // "%s=#%s#,"拼接原字段-小写字段
    private String stringCarrayNames6;                                      // ${item.字段}
    private String tableComment;                                            // 表注释
    private String createDate;                                              // 创建日期
    private String author;                                                  // 生成者

    public Table(String className, String classNameD, String classNameX, String classNameMl, String className_Java,
                 List<TableCarray> tableCarrays, List<TableIndex> tableIndexs, List<TableBind> tableBinds,
                 Set<String> importPojos, String stringCarrayNames1, String stringCarrayNames2,
                 String stringCarrayNames3, String stringCarrayNames4, String stringCarrayNames5,
                 String stringCarrayNames6, String tableComment, String createDate, String author) {
        super();
        this.className = className;
        className_d = classNameD;
        className_x = classNameX;
        className_ml = classNameMl;
        this.className_Java = className_Java;
        this.tableCarrays = tableCarrays;
        this.tableIndexs = tableIndexs;
        this.tableBinds = tableBinds;
        this.importPojos = importPojos;
        this.stringCarrayNames1 = stringCarrayNames1;
        this.stringCarrayNames2 = stringCarrayNames2;
        this.stringCarrayNames3 = stringCarrayNames3;
        this.stringCarrayNames4 = stringCarrayNames4;
        this.stringCarrayNames5 = stringCarrayNames5;
        this.stringCarrayNames6 = stringCarrayNames6;
        this.tableComment = tableComment;
        this.createDate = createDate;
        this.author = author;
    }
}
