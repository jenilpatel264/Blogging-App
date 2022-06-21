package com.blog.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.VO.userVO;
import com.blog.payloads.userDTO;

public interface userService {
	userDTO newRegister(userDTO dto);	
	userDTO createUser(userDTO userDto);
	userDTO updateUser(userDTO dto,Integer userid);
	userDTO getUserById(Integer userId);
	List<userDTO> getAllUsers();
	void delete(Integer userId);
	

}
