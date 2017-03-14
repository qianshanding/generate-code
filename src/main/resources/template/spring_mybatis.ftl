<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
      http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd
      http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.1.xsd">

    <context:component-scan base-package="${daoPackage}"/>

    <bean id="log-filter" class="com.alibaba.druid.filter.logging.Slf4jLogFilter">
        <property name="resultSetLogEnabled" value="false"/>
        <property name="dataSourceLogEnabled" value="false"/>
        <property name="connectionLogEnabled" value="false"/>
        <property name="statementExecutableSqlLogEnable" value="true"/>
    </bean>

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
          init-method="init" destroy-method="close">
        <property name="url" value="jdbc.url"/>
        <property name="username" value="jdbc.username"/>
        <property name="password" value="jdbc.password"/>
        <!-- 初始化连接大小 -->
        <property name="initialSize" value="3"/>
        <!-- 连接池最大数量 -->
        <property name="maxActive" value="20"/>
        <!-- 连接池最小空闲 -->
        <property name="minIdle" value="3"/>
        <!-- 获取连接最大等待时间 -->
        <property name="maxWait" value="60000"/>
        <property name="removeAbandonedTimeout" value="180"/>
        <property name="testWhileIdle" value="true"/>
        <property name="testOnBorrow" value="false"/>
        <property name="testOnReturn" value="false"/>
        <property name="validationQuery" value="SELECT 1"/>
        <property name="validationQueryTimeout" value="1"/>
        <property name="timeBetweenEvictionRunsMillis" value="30000"/>
        <property name="minEvictableIdleTimeMillis" value="120000"/>
        <property name="poolPreparedStatements" value="true"/>
        <property name="defaultAutoCommit" value="true"/>
        <property name="logAbandoned" value="true"/>
        <property name="proxyFilters">
            <list>
                <ref bean="log-filter"/>
            </list>
        </property>
        <property name="connectionProperties" value="socketTimeout=5000"/>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>