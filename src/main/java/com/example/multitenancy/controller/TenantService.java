package com.example.multitenancy.controller;


import com.example.multitenancy.context.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private UserRepository userRepository;

    public void testTenant(String tenantId) {
        TenantContext.setCurrentTenant(tenantId); // Set tenant context
        userRepository.save(new User(tenantId)); // Save data to tenant DB
        System.out.println("Data saved for tenant: " + tenantId);
        TenantContext.clear(); // Clear tenant context
    }

    public String getTestTenant(String tenantId) {
        TenantContext.setCurrentTenant(tenantId);
        User User = userRepository.findByName(tenantId);
        TenantContext.clear();
        return User.getName();
    }
}

