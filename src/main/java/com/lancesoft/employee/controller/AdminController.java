package com.lancesoft.employee.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lancesoft.employee.model.Role;
import com.lancesoft.employee.model.User;
import com.lancesoft.employee.model.UserRole;
import com.lancesoft.employee.repository.UserRepository;
import com.lancesoft.employee.service.impl.UserServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	private static final Logger LOG=LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	@GetMapping("/f")
	public String simplemsg()
	{
		return "hiii";
	}
	//create User
	@PostMapping("/sign-up")
	public User createUser(@RequestBody User user) throws Exception {
		LOG.info("Enterd into createUser Method");

		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		LOG.debug("Encrypted password");
		Set<UserRole> userRoleSet=new HashSet<>();

		Role role=new Role();         //default role "User"
		role.setRoleId(11);
		role.setRoleName("USER");
		

		user.setRoleName(role.getRoleName());

		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		LOG.debug("Assigned Default role to user to USER");
		userRoleSet.add(userRole);
		return this.userService.createUser(user, userRoleSet);
	}

}
