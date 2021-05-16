package com.mybalance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybalance.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	UserRole findByRole(String role);
}