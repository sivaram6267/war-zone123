package com.lancesoft.employee.service;

import java.security.Principal;
import java.util.Set;

import com.lancesoft.employee.model.User;
import com.lancesoft.employee.model.UserRole;
import com.lancesoft.employee.request.PasswordRequest;

public interface UserService {
	
	//creating a new user with roles
	public User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	
	

}
