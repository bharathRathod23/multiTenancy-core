package com.example.multitenancy.testMultitenancy.repository;

import com.example.multitenancy.testMultitenancy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String tenantId);
}
