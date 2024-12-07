package com.example.multitenancy.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@Slf4j
public class MultitenantDataSourceConfig {

    @Value("${default.tenant}")
    private String defaultTenant;

    @Bean
    public DataSource dataSource(DataSourceConfig dataSourceConfigs) {

        log.info("dataSourceConfigs ="+dataSourceConfigs);
        Map<Object, Object> resolvedDataSources = new HashMap<>();
        // Populate data sources from tenant configurations
        for (Map.Entry<String, DataSourceProperties> entry : dataSourceConfigs.getTenants().entrySet()) {
            String tenantId = entry.getKey();
            DataSourceProperties config = entry.getValue();
                DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
                dataSourceBuilder.url(config.getUrl());
                dataSourceBuilder.username(config.getUsername());
                dataSourceBuilder.password(config.getPassword());
                dataSourceBuilder.driverClassName(config.getDriverClassName());
                resolvedDataSources.put(tenantId, dataSourceBuilder.build());

        }

        // Configure the AbstractRoutingDataSource
        AbstractRoutingDataSource routingDataSource = new MultiTenantDataSource();
        routingDataSource.setDefaultTargetDataSource(resolvedDataSources.get(defaultTenant));
        routingDataSource.setTargetDataSources(resolvedDataSources);
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }
}
