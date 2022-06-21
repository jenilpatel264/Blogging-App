package com.blog.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Services.*;
import com.blog.payloads.apiResponse;
import com.blog.payloads.userDTO;

@RestController
@RequestMapping("/api/users")
public class userController {
	@Autowired userService service;
	
	@PostMapping("/")
	public ResponseEntity<userDTO> createUsers(@Valid @RequestBody userDTO dto)
	{
		userDTO dto2=this.service.createUser(dto);
		return new ResponseEntity<>(dto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<userDTO> update(@Valid @RequestBody userDTO dto,@PathVariable Integer userid)
	{
		userDTO dto2=this.service.updateUser(dto, userid);
		return ResponseEntity.ok(dto2);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userid}")
	public ResponseEntity<apiResponse> delete(@PathVariable Integer userid)
	{
		this.service.delete(userid);
		return new ResponseEntity(new apiResponse("delete successfull", true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<userDTO>> allusers()
	{
		return ResponseEntity.ok(this.service.getAllUsers());
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<userDTO> singleuser(@PathVariable Integer userid)
	{
		return ResponseEntity.ok(this.service.getUserById(userid));
	}

}
