package com.shl.HrManagement.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;
	private String password;
	
}
