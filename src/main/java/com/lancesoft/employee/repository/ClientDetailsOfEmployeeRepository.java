package com.lancesoft.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancesoft.employee.model.ClientDetailsOfEmployee;
@Repository
public interface ClientDetailsOfEmployeeRepository extends JpaRepository<ClientDetailsOfEmployee, Integer> {

}
