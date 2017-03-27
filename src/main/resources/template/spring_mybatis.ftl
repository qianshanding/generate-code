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
        <property name="connectionInitSqls" value="set names utf8mb4"/>
        <!-- 连接池初始连接数 -->
        <property name="initialSize" value="5"/>
        <!-- 允许的最大同时使用中(在被业务线程持有，还没有归还给druid) 的连接数 -->
        <property name="maxActive" value="20"/>
        <!-- 允许的最小空闲连接数，空闲连接超时踢除过程会最少保留的连接数 -->
        <property name="minIdle" value="5"/>
        <!-- 从连接池获取连接的最大等待时间 5 秒-->
        <property name="maxWait" value="5000"/>
        <!-- 一条物理连接的最大存活时间 120分钟-->
        <property name="phyTimeoutMillis" value="7200000"/>
        <!-- 强行关闭从连接池获取而长时间未归还给druid的连接(认为异常连接）-->
        <property name="removeAbandoned" value="true"/>
        <!-- 异常连接判断条件，超过180 秒 则认为是异常的，需要强行关闭 -->
        <property name="removeAbandonedTimeout" value="180"/>
        <!-- 从连接池获取到连接后，如果超过被空闲剔除周期，是否做一次连接有效性检查 -->
        <property name="testWhileIdle" value="true"/>
        <!-- 从连接池获取连接后，是否马上执行一次检查 -->
        <property name="testOnBorrow" value="false"/>
        <!-- 归还连接到连接池时是否马上做一次检查 -->
        <property name="testOnReturn" value="false"/>
        <!-- 连接有效性检查的SQL -->
        <property name="validationQuery" value="SELECT 1"/>
        <!-- 连接有效性检查的超时时间 1 秒 -->
        <property name="validationQueryTimeout" value="1"/>
        <!-- 周期性剔除长时间呆在池子里未被使用的空闲连接, 15秒一次-->
        <property name="timeBetweenEvictionRunsMillis" value="15000"/>
        <!-- 空闲多久可以认为是空闲太长而需要剔除 54 秒-->
        <property name="minEvictableIdleTimeMillis" value="54000"/>
        <!-- 如果空闲时间太长即使连接池所剩连接 < minIdle 也要被剔除 54 秒 -->
        <property name="maxEvictableIdleTimeMillis" value="54000"/>
        <property name="poolPreparedStatements" value="true"/>
        <!-- 是否设置自动提交，相当于每个语句一个事务 -->
        <property name="defaultAutoCommit" value="true"/>
        <!-- 记录被判定为异常的连接 -->
        <property name="logAbandoned" value="true"/>
        <!-- 网络读取超时，网络连接超时
             socketTimeout : 对于线上业务小于5s，对于BI等执行时间较长的业务的SQL，需要设置大一点
        -->
        <property name="connectionProperties" value="socketTimeout=3000;connectTimeout=1000"/>
        <property name="proxyFilters">
            <list>
                <ref bean="log-filter"/>
            </list>
        </property>
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