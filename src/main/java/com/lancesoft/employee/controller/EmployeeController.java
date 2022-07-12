package com.lancesoft.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lancesoft.employee.model.BasicDetails;
import com.lancesoft.employee.repository.BasicDetailsRepository;
import com.lancesoft.employee.service.impl.EmployeeService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {
	@Autowired
	BasicDetailsRepository basicDetailsRepo;

	@Autowired
	EmployeeService dateHandler;
	
	@Autowired
	private EmployeeService employeeService;
   
	@GetMapping("/getting/{id}")
	public ResponseEntity<BasicDetails> dateHandler(@RequestParam int id) {

	Optional<BasicDetails> ls = basicDetailsRepo.findById(id);
	System.out.println(ls);
		BasicDetails Basic = null;
		if (ls.isPresent()) {
			Basic = ls.get();}
		System.out.println(Basic+"-----------------------------------------------");
		
		BasicDetails BB = dateHandler.EMSHandler(Basic);

		return new ResponseEntity<BasicDetails>(BB, HttpStatus.OK);
		
	}

	@GetMapping("/get-all-employee")
	public ResponseEntity<Map<String, Object>> findAllEmp(
			@RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue="20", required=false)Integer pageSize) {
		
		//final String uri="http://localhost:2022/api/getting";
		try {
			Page<BasicDetails> details=	dateHandler.gettingAll(PageRequest.of(pageNumber, pageSize));	
			Map<String, Object> response = new HashMap<>();
			List<BasicDetails> allEmployee=details.getContent();
			System.out.println("------------"+response);
			response.put("User", allEmployee);
			response.put("currentPage", details.getNumber());
			response.put("totalItems", details.getTotalElements());
			response.put("totalPages", details.getTotalPages());
			List<BasicDetails> basicDeatils=allEmployee;
			List<Integer> bdId=basicDeatils.stream().map(BasicDetails::getId).collect(Collectors.toList());
			System.out.println("----------------------------"+bdId);
			//List<BasicDetails> ls = basicDetailsRepo.findAllById(bdId);
			//dateHandler.EMSHandler(ls);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		}
	
	
	
	@PostMapping("/insert-emp-details")
	public ResponseEntity<BasicDetails> insertEmployee(@RequestBody BasicDetails basicDetails){
		 employeeService.createEmployee(basicDetails);
		 return new ResponseEntity<BasicDetails>(basicDetails, HttpStatus.ACCEPTED);
	}
}

