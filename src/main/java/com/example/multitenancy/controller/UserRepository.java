package com.example.multitenancy.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String tenantId);
}
