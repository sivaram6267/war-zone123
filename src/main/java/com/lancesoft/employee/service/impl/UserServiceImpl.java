package com.lancesoft.employee.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lancesoft.employee.exception.UserAlreadinExistException;
import com.lancesoft.employee.model.User;
import com.lancesoft.employee.model.UserRole;
import com.lancesoft.employee.repository.RoleRepository;
import com.lancesoft.employee.repository.UserRepository;
import com.lancesoft.employee.request.PasswordRequest;
import com.lancesoft.employee.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User already exist with this username!!");
			throw new UserAlreadinExistException("user already present with this username","present");
			//create user
		}
		else {

			for(UserRole ur: userRole) {
				roleRepository.save(ur.getRole());//role save
			}

			user.getUserRole().addAll(userRole);//associating roles to user
			local=this.userRepository.save(user);
		}
		return local;
	}

}
