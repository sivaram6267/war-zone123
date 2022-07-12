package com.lancesoft.employee.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
	@Id
	@GeneratedValue(generator = "billing_gen",strategy = GenerationType.AUTO)
	private int billingEmpId;
	
	private long paidTillNow;//from joining date to till now
	private long benchExp;// monthly(cubiclcost+foodCost+transportCost)
	private long cubicalCost;//monthly
	private long foodCost;//monthly
	private long transportCost;//monthly 
	private long profit;//monthly
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "emp_id_fk")
	@JsonBackReference
	private BasicDetails basicDetails;
	
}
