package com.blog.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.VO.categoryVO;
import com.blog.VO.postVO;

public interface categoryDAO extends JpaRepository<categoryVO, Integer>{

	
}
