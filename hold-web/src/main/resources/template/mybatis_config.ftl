<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC
        "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="multipleResultSetsEnabled" value="true"/>
        <setting name="useColumnLabel" value="true"/>
        <setting name="useGeneratedKeys" value="false"/>
        <setting name="defaultExecutorType" value="SIMPLE"/>
        <setting name="defaultStatementTimeout" value="25000"/>
    </settings>

    <typeAliases>
    <#list tableList as tableName>
    <typeAlias alias="${tableName}" type="${doPackage}.${tableName}DO"/>
    </#list>
    </typeAliases>

    <mappers>
    <#list tableMlList as tableName>
        <mapper resource="${mybatisXmlPath}/sqlmap-${tableName}.xml"/>
    </#list>
    </mappers>
</configuration>
