package com.shl.HrManagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shl.HrManagement.payloads.RoleDto;
import com.shl.HrManagement.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	// create role
	@PostMapping("/")
	public ResponseEntity<RoleDto> createEmployee(@RequestBody RoleDto roleDto){
		RoleDto createdRole = this.roleService.createRole(roleDto);
		return new ResponseEntity<RoleDto>(createdRole, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<RoleDto>> getAllEmployees(){
		List<RoleDto> roles = this.roleService.getAllRoles();
		return new ResponseEntity<List<RoleDto>>(roles, HttpStatus.OK);
	}
}
