package com.blog.Controllers;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Services.userService;
import com.blog.exceptions.ApiException;
import com.blog.payloads.JwtAuthRequests;
import com.blog.payloads.JwtAuthResponse;
import com.blog.payloads.userDTO;
import com.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

	@Autowired
	private JwtTokenHelper helper;
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired userService service;
	

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequests authRequests) throws Exception {
		this.authenticate(authRequests.getUsername(), authRequests.getPassword());
		UserDetails details=this.detailsService.loadUserByUsername(authRequests.getUsername());
		String token=this.helper.generateToken(details);
		JwtAuthResponse authResponse=new JwtAuthResponse();
		authResponse.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			throw new ApiException("invalid username or password");
		}
		
		
	}
	@PostMapping("/register")
	private ResponseEntity<userDTO> register(@RequestBody userDTO dto)
	{
		userDTO dto2=this.service.newRegister(dto);
		return new ResponseEntity<userDTO>(dto2,HttpStatus.OK);
	}

}
