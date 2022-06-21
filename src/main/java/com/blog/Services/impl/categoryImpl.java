package com.blog.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DAO.categoryDAO;
import com.blog.Services.*;
import com.blog.VO.categoryVO;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.categoryDTO;
@Service
public class categoryImpl implements catergoryService {

	@Autowired categoryDAO categoryDAO;
	
	@Autowired
	private ModelMapper mapper;
	@Override
	public categoryDTO createCategory(categoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		categoryVO categoryVO=this.mapper.map(categoryDTO, categoryVO.class);
		categoryVO categoryVO2=this.categoryDAO.save(categoryVO);
		return this.mapper.map(categoryVO2,categoryDTO.class);
	}

	@Override
	public categoryDTO updateCategory(categoryDTO categoryDTO, int categoryId) {
		// TODO Auto-generated method stub
		categoryVO categoryVO=this.categoryDAO.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryVO", "category Id", categoryId));
		categoryVO.setCategoryTitle(categoryDTO.getCategoryTitle());
		categoryVO.setCategoryDescription(categoryDTO.getCategoryDescription());
		categoryVO categoryVO2=this.categoryDAO.save(categoryVO);
		return this.mapper.map(categoryVO2, categoryDTO.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		categoryVO categoryVO=this.categoryDAO.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryVO", "category Id", categoryId));
		this.categoryDAO.delete(categoryVO);
		
	}

	@Override
	public List<categoryDTO> getCategory() {
		// TODO Auto-generated method stub
		List<categoryVO> categoryVOs=this.categoryDAO.findAll();
		List<categoryDTO> cats=categoryVOs.stream().map((cat)->this.mapper.map(cat, categoryDTO.class)).collect(Collectors.toList());
		return cats;
	}

	@Override
	public categoryDTO CategoryById(int categoryId) {
		// TODO Auto-generated method stub
		categoryVO categoryVO=this.categoryDAO.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryVO", "category Id", categoryId));
		
		return this.mapper.map(categoryVO, categoryDTO.class);
	}

}
