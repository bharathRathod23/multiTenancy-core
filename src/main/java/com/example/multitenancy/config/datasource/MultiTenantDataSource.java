package com.example.multitenancy.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiTenantDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return com.example.multitenancy.context.TenantContext.getCurrentTenant();
    }
}