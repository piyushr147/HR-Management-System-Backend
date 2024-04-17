package com.shl.HrManagement.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shl.HrManagement.entities.Employee;
import com.shl.HrManagement.entities.Role;
import com.shl.HrManagement.payloads.EmployeeDto;
import com.shl.HrManagement.payloads.RoleDto;
import com.shl.HrManagement.repositories.RoleRepo;
import com.shl.HrManagement.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public RoleDto createRole(RoleDto roleDto) {
		Role role = new Role();
		role.setRole_id(roleDto.getRole_id());
		role.setRole_type(roleDto.getRole_type());
		role = this.roleRepo.save(role);
		return this.modelMapper.map(role, RoleDto.class);
	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> roles = this.roleRepo.findAll();
		List<RoleDto> rolesDto = roles.stream().map(role -> this.modelMapper.map(role,RoleDto.class)).collect(Collectors.toList());
		return rolesDto;
	}
}
