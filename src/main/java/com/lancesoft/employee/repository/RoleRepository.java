package com.lancesoft.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancesoft.employee.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {


}
