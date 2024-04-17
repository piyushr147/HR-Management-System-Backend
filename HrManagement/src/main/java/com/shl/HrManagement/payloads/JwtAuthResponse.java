package com.shl.HrManagement.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {
	private String token;
	
	private EmployeeDto employeeDto;
}
