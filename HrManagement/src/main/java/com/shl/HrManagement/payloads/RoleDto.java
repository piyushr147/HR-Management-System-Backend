package com.shl.HrManagement.payloads;

import java.util.List;

import javax.persistence.ManyToMany;

import com.shl.HrManagement.entities.Employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {
	private int role_id;
	private String role_type;	
	
	private List<EmployeeDto> employeesDto;
}

