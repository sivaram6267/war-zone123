
package com.lancesoft.employee.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

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
public class ClientDetailsOfEmployee {
	@Id
	@GeneratedValue(generator = "client_emp_gen",strategy = GenerationType.AUTO)
	private int clientEmpId;
	
	private String clientName;
	
	private long clientBilling;// monthly client salary to emp
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date poSdate;// purches order start date 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date poEdate;// purches order end  date 
	
	private String designationAtClient;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "emp_id_fk")
	@JsonBackReference
	private BasicDetails basicDetails;
	

}
