package com.blog.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Services.catergoryService;
import com.blog.Services.userService;
import com.blog.payloads.apiResponse;
import com.blog.payloads.categoryDTO;
import com.blog.payloads.userDTO;

@RestController
@RequestMapping("/api/categories")
public class categoryController {
	
	@Autowired catergoryService catergoryService;
	@PostMapping("/")
	public ResponseEntity<categoryDTO> createCategory(@Valid @RequestBody categoryDTO categoryDTO)
	{
		categoryDTO categoryDTO2=this.catergoryService.createCategory(categoryDTO);
		return new ResponseEntity<categoryDTO>(categoryDTO2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<categoryDTO> updatecategory(@Valid @RequestBody categoryDTO categoryDTO,@PathVariable Integer categoryId)
	{
		categoryDTO categoryDTO2=this.catergoryService.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<categoryDTO>(categoryDTO2,HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<apiResponse> deleteCategory(@PathVariable Integer categoryId)
	{
		this.catergoryService.deleteCategory(categoryId);
		return new ResponseEntity<apiResponse>(new apiResponse("successfully deleted",false),HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<categoryDTO>> getAllCategory()
	{
	
		return ResponseEntity.ok(this.catergoryService.getCategory());
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<categoryDTO> updateCategory(@PathVariable Integer categoryId)
	{
		categoryDTO categoryDTO2=this.catergoryService.CategoryById(categoryId);
		return new ResponseEntity<categoryDTO>(categoryDTO2,HttpStatus.OK);
	}
	

}
