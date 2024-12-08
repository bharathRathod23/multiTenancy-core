package com.example.multitenancy.testMultitenancy.service;


import com.example.multitenancy.context.TenantContext;
import com.example.multitenancy.testMultitenancy.entity.User;
import com.example.multitenancy.testMultitenancy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private UserRepository userRepository;

    public void saveTenantInfo(String tenantId) {
        TenantContext.setCurrentTenant(tenantId); // Set tenant context
        userRepository.save(new User(tenantId)); // Save data to tenant DB
        System.out.println("Data saved for tenant: " + tenantId);
        TenantContext.clear(); // Clear tenant context
    }

    public String getTenantInfo(String tenantId) {
        TenantContext.setCurrentTenant(tenantId);
        User User = userRepository.findByName(tenantId);
        TenantContext.clear();
        return User.getName();
    }
}

