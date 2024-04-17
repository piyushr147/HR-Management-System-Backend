package com.shl.HrManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shl.HrManagement.entities.Employee;
import com.shl.HrManagement.entities.Role;
import com.shl.HrManagement.payloads.RoleDto;
import com.shl.HrManagement.repositories.EmployeeRepo;
import com.shl.HrManagement.repositories.RoleRepo;

@SpringBootApplication
public class HrManagementApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(HrManagementApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("12345"));
//		try {
//			
//			Role role1 = new Role();
//			role1.setRole_type("ROLE_SUPER_ADMIN");
//			roleRepo.save(role1);
//			
//			Role role2 = new Role();
//			role2.setRole_type("ROLE_HR_PARTNER");
//			roleRepo.save(role2);
//			
//			Role role3 = new Role();
//			role3.setRole_type("ROLE_HR_ADMIN");
//			roleRepo.save(role3);
//			
//			Role role4 = new Role();
//			role4.setRole_type("ROLE_NORMAL");
//			roleRepo.save(role4);
//			
//			Employee emp = new Employee();
//			emp.setFirstName("Jai");
//			emp.setLastName("Minocha");
//			emp.setAge(22);
//			emp.setEmail("jai@shl.com");
//			emp.setDesignation("Software intern");
//			emp.setPassword(passwordEncoder.encode("12345"));
//			emp.setSalary(1000000);
//			
//			
//			emp.getRoles().add(role1);
//			this.employeeRepo.save(emp);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
