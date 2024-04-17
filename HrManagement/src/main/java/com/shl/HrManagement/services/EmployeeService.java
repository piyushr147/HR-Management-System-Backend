package com.shl.HrManagement.services;

import org.springframework.stereotype.Service;

import com.shl.HrManagement.payloads.EmployeeDto;
import java.util.List;

@Service
public interface EmployeeService {
	EmployeeDto registerNewUser(EmployeeDto employeeDto);
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	EmployeeDto updateEmployee(EmployeeDto employee, Integer employeeId);
	EmployeeDto updateSelf(EmployeeDto employeeDto, Integer employeeId);
	EmployeeDto getEmployeeById(Integer employeeId);
	List<EmployeeDto> getAllEmployees();
	void deleteEmployee(Integer employeeId);
}
