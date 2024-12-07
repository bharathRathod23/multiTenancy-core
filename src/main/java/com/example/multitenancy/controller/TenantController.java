package com.example.multitenancy.controller;

import com.example.multitenancy.config.datasource.DataSourceConfig;
import com.example.multitenancy.config.datasource.DataSourceProperties;
import com.example.multitenancy.context.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Autowired
    TenantService tenantService;

    @GetMapping("/{tenantId}")
    public String getTenant(@PathVariable String tenantId) {
        // Example response, can be integrated with TenantService
       DataSourceProperties config =  dataSourceConfig.getTenants().get(TenantContext.getCurrentTenant());
       String url = config.getUrl();
       String tenant = TenantContext.getCurrentTenant();
        return "URL details : " + url;
    }

    @PostMapping
    public String createTenant(@RequestParam String tenantId) {
        // Logic to create a tenant
        return "Tenant created: " + tenantId;
    }

    @DeleteMapping("/{tenantId}")
    public String deleteTenant(@PathVariable String tenantId) {
        // Logic to delete a tenant
        return "Tenant deleted: " + tenantId;
    }


    @GetMapping("/testTenant")
    public String testTenant(@RequestParam String tenantId) {
        tenantService.testTenant(tenantId);
        return "Test completed for tenant: " + tenantId;
    }

    @GetMapping("/getTenant")
    public String getTestTenant(@RequestParam String tenantId) {


        return tenantService.getTestTenant(tenantId);

    }
    }
