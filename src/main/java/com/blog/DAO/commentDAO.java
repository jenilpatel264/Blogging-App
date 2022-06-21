package com.blog.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.VO.commentVO;
@Repository
public interface commentDAO extends JpaRepository<commentVO, Integer>{

}
