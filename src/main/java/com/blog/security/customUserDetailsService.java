package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.DAO.userDAO;
import com.blog.VO.userVO;
import com.blog.exceptions.ResourceNotFoundException;
@Service
public class customUserDetailsService implements UserDetailsService{

	@Autowired userDAO dao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userVO userVO=this.dao.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("userVO","email"+username,0));
		return userVO;
	}

}
