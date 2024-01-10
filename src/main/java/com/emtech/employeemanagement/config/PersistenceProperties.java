package com.emtech.employeemanagement.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource(value = {"classpath:persistence.properties"})
public class PersistenceProperties {
    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.host}")
    private String host;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.port}")
    private Integer port;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.name}")
    private  String database;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.schema}")
    private String schema;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.username}")
    private String username;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.password}")
    private String password;

    /* hibernate */
    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.generator.mappings}")
    private boolean generateMappings;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.lob.non.contextual.creation}")
    private boolean lobNonContextualCreation;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.driver.class.name}")
    private String driverClass;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.dialect}")
    private String dialect;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.packages.to.scan}")
    private String[] packagesToScan;

    /* hikari */
    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.hikari.pool.name}")
    private String hikariPoolName;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.hikari.pool.max.connections}")
    private Integer hikariPoolMaxConnections;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.hikari.pool.connection.timeout}")
    private Long hikariPoolConnectionTimeout;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.hikari.pool.idle.timeout}")
    private Long hikariPoolIdleTimeout;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.hikari.pool.max.lifetime}")
    private Long hikariPoolMaxLifetime;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.hikari.pool.validation.timeout}")
    private Long hikariPoolValidationTimeout;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.hikari.pool.leak.detection.threshold}")
    private Long hikariPoolLeakDetectionThreshold;

    /* cache */
    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.cache.prepare.statements}")
    private boolean cachePrepareStatements;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.cache.prepare.statements.size}")
    private Long cachePrepareStatementsSize;

    @Value(value = "${com.emtech.employeemanagement.config.properties.persistence.cache.prepare.statements.sql.limit}")
    private Long cachePrepareStatementsSqlLimit;
}
