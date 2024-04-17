package com.shl.HrManagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shl.HrManagement.payloads.RoleDto;

@Service
public interface RoleService {
	RoleDto createRole(RoleDto roleDto);
	List<RoleDto> getAllRoles();
}
