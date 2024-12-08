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
        userRepository.save(new User(tenantId));
        System.out.println("Data saved for tenant: " + tenantId);}

    public String getTenantInfo(String tenantId) {
        User User = userRepository.findByName(tenantId);
        return User.getName();
    }
}

