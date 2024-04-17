package com.shl.HrManagement.controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shl.HrManagement.entities.Employee;
import com.shl.HrManagement.exceptions.ApiException;
import com.shl.HrManagement.payloads.EmployeeDto;
import com.shl.HrManagement.payloads.JwtAuthRequest;
import com.shl.HrManagement.payloads.JwtAuthResponse;
import com.shl.HrManagement.repositories.EmployeeRepo;
import com.shl.HrManagement.security.JwtTokenHelper;
import com.shl.HrManagement.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private ModelMapper mapper;

	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setEmployeeDto(this.mapper.map((Employee) userDetails, EmployeeDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	// register new user api

	@PostMapping("/register")
	public ResponseEntity<EmployeeDto> registerUser(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto registeredUser = this.employeeService.registerNewUser(employeeDto);
		return new ResponseEntity<EmployeeDto>(registeredUser, HttpStatus.CREATED);
	}

	// get loggedin user data
	
	@GetMapping("/current-user/")
	public ResponseEntity<EmployeeDto> getUser(Principal principal) {
		Employee user = this.employeeRepo.findByEmail(principal.getName()).get();
		return new ResponseEntity<EmployeeDto>(this.mapper.map(user, EmployeeDto.class), HttpStatus.OK);
	}
}
