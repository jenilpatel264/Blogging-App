package com.blog.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.VO.userVO;
@Repository
public interface userDAO extends JpaRepository<userVO, Integer>{
	Optional<userVO> findByEmail(String email);

}
