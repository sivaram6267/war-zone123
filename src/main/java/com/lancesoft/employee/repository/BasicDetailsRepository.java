package com.lancesoft.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancesoft.employee.model.BasicDetails;

public interface BasicDetailsRepository extends JpaRepository<BasicDetails, Integer> {

	BasicDetails findByEmployeeId(String employeeId);

}
