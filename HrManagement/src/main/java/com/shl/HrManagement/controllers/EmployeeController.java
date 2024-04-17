package com.shl.HrManagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shl.HrManagement.payloads.ApiResponse;
import com.shl.HrManagement.payloads.EmployeeDto;
import com.shl.HrManagement.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'HR_ADMIN')")
	@PostMapping("/")
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
		EmployeeDto createdEmployee = this.employeeService.createEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(createdEmployee, HttpStatus.CREATED);
	}
	
	// update anyone
	@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'HR_ADMIN', 'HR_PARTNER')")
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer employeeId){
		EmployeeDto updatedEmployee = this.employeeService.updateEmployee(employeeDto, employeeId);
		return new ResponseEntity<EmployeeDto>(updatedEmployee, HttpStatus.OK);
	}
	
	// update self : anyone can update their own data
	@PutMapping("/employee/{employeeId}")
	public ResponseEntity<EmployeeDto> updateSelf(@RequestBody EmployeeDto employeeDto, @PathVariable Integer employeeId){
		EmployeeDto updatedEmp = this.employeeService.updateSelf(employeeDto, employeeId);
		return new ResponseEntity<EmployeeDto>(updatedEmp, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer employeeId){
		this.employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'HR_ADMIN', 'HR_PARTNER')")
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		return ResponseEntity.ok(this.employeeService.getAllEmployees());
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> getSingleEmployee(@PathVariable Integer employeeId){
		return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
	}
}
