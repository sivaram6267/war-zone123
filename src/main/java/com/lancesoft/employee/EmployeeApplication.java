package com.lancesoft.employee;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lancesoft.employee.model.Role;
import com.lancesoft.employee.model.User;
import com.lancesoft.employee.model.UserRole;
import com.lancesoft.employee.service.UserService;

@SpringBootApplication
public class EmployeeApplication extends SpringBootServletInitializer  implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(EmployeeApplication.class);
	    }
	
	@Override
	public void run(String... args) throws Exception{
		
		/*BasicDetails bd=new BasicDetails();
		Date d=new Date(2022-02-22);
		bd.setJoiningDate(d);
		bd.setEmployeeName("xyz");*/
		
		/*System.out.println("execution start");
		
		User user=new User();
		
		user.setEmail("user1@gmail.com");
		user.setFirstName("Test");
		user.setLastName("User");
		user.setDOB("01-06-1998");
		user.setPhoneNo("7350957167");
		user.setPassword(bCryptPasswordEncoder.encode("user"));
		user.setUsername("user");
		
		Role role=new Role();
		role.setRoleId(11);
		role.setRoleName("USER");
		
		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		
		User user1=this.userService.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());
		
		*/

	}

}
