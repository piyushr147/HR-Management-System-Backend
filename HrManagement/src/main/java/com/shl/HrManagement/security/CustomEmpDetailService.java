package com.shl.HrManagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shl.HrManagement.entities.Employee;
import com.shl.HrManagement.exceptions.ResourceNotFoundException;
import com.shl.HrManagement.repositories.EmployeeRepo;

// implements interface:  Loads the user details
@Service
public class CustomEmpDetailService implements UserDetailsService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from db by username
		Employee employee = this.employeeRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Employee", "email" + username, 0)); 
		return employee;
	}

}
