package com.shl.HrManagement.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shl.HrManagement.entities.Employee;
import com.shl.HrManagement.entities.Role;
import com.shl.HrManagement.exceptions.ApiException;
import com.shl.HrManagement.exceptions.ResourceNotFoundException;
import com.shl.HrManagement.payloads.EmployeeDto;
import com.shl.HrManagement.payloads.RoleDto;
import com.shl.HrManagement.repositories.EmployeeRepo;
import com.shl.HrManagement.repositories.RoleRepo;
import com.shl.HrManagement.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
		
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Optional<Employee> emp = this.employeeRepo.findByEmail(employeeDto.getEmail());
		
		if (!emp.isEmpty()) {
			throw new ApiException("Email already registered");
		}
		
//		Employee employee = this.modelMapper.map(employeeDto, Employee.class);
		
		if (employeeDto.getPassword().isEmpty()) throw new ApiException("Password cannot be blank");
		Employee employee = new Employee();
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setAge(employeeDto.getAge());
		employee.setEmail(employeeDto.getEmail());
		employee.setSalary(employeeDto.getSalary());
		employee.setDesignation(employeeDto.getDesignation());

		// set role
		List<RoleDto> listOfRoles = employeeDto.getRoles();
		for (var rdto : listOfRoles) {
			Optional<Role> role = roleRepo.findById(rdto.getRole_id());
			
			employee.getRoles().add(role.get());
		}
		
		employee.setPassword(this.passwordEncoder.encode(employeeDto.getPassword()));
		Employee newEmp = this.employeeRepo.save(employee);
		return this.modelMapper.map(newEmp, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer employeeId) {
		Employee currentEmployee = this.employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeID", employeeId));

		currentEmployee.setFirstName(employeeDto.getFirstName());
		currentEmployee.setLastName(employeeDto.getLastName());
		currentEmployee.setAge(employeeDto.getAge());
		currentEmployee.setEmail(employeeDto.getEmail());
		if (!employeeDto.getPassword().isEmpty()) currentEmployee.setPassword(this.passwordEncoder.encode(employeeDto.getPassword()));
		
		currentEmployee.setSalary(employeeDto.getSalary());
		currentEmployee.setDesignation(employeeDto.getDesignation());

		List<RoleDto> listRoles = employeeDto.getRoles();
		List<Role> listOfRoles = new ArrayList<Role>();
		for (int i = 0; i < listRoles.size(); i++) {
			Role role = this.modelMapper.map(listRoles.get(i), Role.class);
			listOfRoles.add(role);
		}
		currentEmployee.setRoles(listOfRoles);
		
		this.employeeRepo.save(currentEmployee);
		
		return this.modelMapper.map(currentEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto getEmployeeById(Integer employeeId) {
		Employee employee = this.employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeID", employeeId));
		return this.modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = this.employeeRepo.findAll();
		List<EmployeeDto> employeeDtos = employees.stream().map(employee -> this.modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
		return employeeDtos;
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		Employee employee = this.employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeID", employeeId));
		this.employeeRepo.delete(employee);
	}

	@Override
	public EmployeeDto registerNewUser(EmployeeDto employeeDto) {
		Employee employee = this.modelMapper.map(employeeDto, Employee.class);
		// encoded the password
		employee.setPassword(this.passwordEncoder.encode(employeeDto.getPassword()));
		
		// roles
		Role role = this.roleRepo.findById(2).get();
		employee.getRoles().add(role);
		Employee newEmp = this.employeeRepo.save(employee);
		return this.modelMapper.map(newEmp, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateSelf(EmployeeDto employeeDto, Integer employeeId) {
		Employee currentEmployee = this.employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeID", employeeId));

		currentEmployee.setFirstName(employeeDto.getFirstName());
		currentEmployee.setLastName(employeeDto.getLastName());
		currentEmployee.setAge(employeeDto.getAge());
		currentEmployee.setEmail(employeeDto.getEmail());
		currentEmployee.setPassword(this.passwordEncoder.encode(employeeDto.getPassword()));
		currentEmployee.setSalary(employeeDto.getSalary());
		currentEmployee.setDesignation(employeeDto.getDesignation());
		this.employeeRepo.save(currentEmployee);
		
		return this.modelMapper.map(currentEmployee, EmployeeDto.class);
	}

}
