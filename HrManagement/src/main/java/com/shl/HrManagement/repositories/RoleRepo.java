package com.shl.HrManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shl.HrManagement.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	
}
