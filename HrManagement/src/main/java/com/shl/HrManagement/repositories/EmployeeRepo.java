package com.shl.HrManagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shl.HrManagement.entities.Employee;

// To store the data of the user 
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	
	Optional<Employee> findByEmail(String email);
}
