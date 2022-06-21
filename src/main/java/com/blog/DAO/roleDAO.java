package com.blog.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import com.blog.VO.roleVO;

public interface roleDAO extends JpaRepository<roleVO, Integer>{

}
