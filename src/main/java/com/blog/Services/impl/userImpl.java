package com.blog.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.blog.exceptions.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.Config.appConstants;
import com.blog.DAO.roleDAO;
import com.blog.DAO.userDAO;
import com.blog.Services.userService;
import com.blog.VO.roleVO;
import com.blog.VO.userVO;
import com.blog.payloads.userDTO;
@Service
public class userImpl implements userService {

	@Autowired
	private userDAO userDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired PasswordEncoder encoder;
	
	@Autowired roleDAO dao;

	@Override
	public userDTO createUser(userDTO userDto) {
		// TODO Auto-generated method stub
		userVO userVO=this.dtotovo(userDto);
		userVO userVO2=this.userDAO.save(userVO);
		return this.votodto(userVO2);
	}

	@Override
	public userDTO updateUser(userDTO dto, Integer userid) {
		// TODO Auto-generated method stub
		userVO userVO=this.userDAO.findById(userid).orElseThrow(()-> new ResourceNotFoundException("userVO","id",userid));
		userVO.setName(dto.getName());
		userVO.setEmail(dto.getEmail());
		userVO.setPassword(dto.getPassword());
		userVO.setAbout(dto.getAbout());
		userVO update=this.userDAO.save(userVO);
		return this.votodto(update);
	}

	@Override
	public userDTO getUserById(Integer userId) {
		userVO userVO=this.userDAO.findById(userId).orElseThrow(()->new ResourceNotFoundException("userVO","id",userId));
		return this.votodto(userVO);
	}

	@Override
	public List<userDTO> getAllUsers() {
		List<userVO> list=this.userDAO.findAll();
		List<userDTO> userlist=list.stream().map(user->this.votodto(user)).collect(Collectors.toList());
		return userlist;
	}

	@Override
	public void delete(Integer userId) {
		userVO userVO=this.userDAO.findById(userId).orElseThrow(()->new ResourceNotFoundException("userVO","id",userId));
		this.userDAO.delete(userVO);
	}

	public userVO dtotovo(userDTO dto) {

		userVO userVO = this.modelMapper.map(dto, userVO.class);
//		userVO.setId(dto.getId());
//		userVO.setName(dto.getName());
//		userVO.setEmail(dto.getEmail());
//		userVO.setPassword(dto.getPassword());
//		userVO.setAbout(dto.getAbout());
		return userVO;

	}

	public userDTO votodto(userVO userVO) {
		userDTO dto = this.modelMapper.map(userVO, userDTO.class);
//		dto.setId(userVO.getId());
//		dto.setName(userVO.getName());
//		dto.setEmail(userVO.getEmail());
//		dto.setPassword(userVO.getPassword());
//		dto.setAbout(userVO.getAbout());
		return dto;

	}

	@Override
	public userDTO newRegister(userDTO dto) {
		userVO userVO=this.modelMapper.map(dto, userVO.class);
		userVO.setPassword(this.encoder.encode(userVO.getPassword()));
	roleVO roleVO=this.dao.findById(appConstants.normal_user).get();
		userVO.getRoleVOs().add(roleVO);
		userVO userVO2=this.userDAO.save(userVO);
		return this.modelMapper.map(userVO2, userDTO.class);
	}

}
