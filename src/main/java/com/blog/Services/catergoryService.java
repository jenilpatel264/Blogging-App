package com.blog.Services;

import java.util.List;

import com.blog.payloads.categoryDTO;

public interface catergoryService {
	
	public categoryDTO createCategory(categoryDTO categoryDTO);
	public categoryDTO updateCategory(categoryDTO categoryDTO,int categoryId);
	public void deleteCategory(int categoryId);
	public List<categoryDTO> getCategory();
	public categoryDTO CategoryById(int categoryId);
	

}
