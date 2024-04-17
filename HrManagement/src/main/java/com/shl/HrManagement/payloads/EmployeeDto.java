package com.shl.HrManagement.payloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shl.HrManagement.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=3, message="first name must be a minimium of 3 characters")
	private String firstName;
	
	@Size(min=2, message="last name must be a minimum of 2 characters")
	private String lastName;
	
	private int age;
	private double salary;
	private String designation;
	
	@Email(message="Your email address is not valid")
	private String email;
	
//	@NotEmpty
//	@Size(min=4, max=20, message="Password must be a minimum of 4 characters")
	private String password;
	
	private List<RoleDto> roles = new ArrayList<>();

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
}
