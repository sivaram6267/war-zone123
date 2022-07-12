package com.lancesoft.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancesoft.employee.model.Bill;
@Repository
public interface BillingRepository extends JpaRepository<Bill, Integer> {

}
