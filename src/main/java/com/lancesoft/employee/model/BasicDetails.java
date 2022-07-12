package com.lancesoft.employee.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Entity
@Data
public class BasicDetails {
	
	@Id
	@GeneratedValue(generator = "bd_gen",strategy = GenerationType.AUTO)
    private Integer id;// auto generated id for persisten op
	
	private String employeeId;// Lancesoft emp id
	
	private String employeeName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joiningDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	private int salary;
	
	private String practice;
	
	private String designationAtLs;
	
	private int benchTen;//joinig date to purches order start date (pOSdate) 
	
	private int tenure;//(joingdate to current date ) in months 
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Bill bill;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private ClientDetailsOfEmployee clientDetailsOfEmployee;
	
}
