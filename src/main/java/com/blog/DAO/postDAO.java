package com.blog.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.VO.categoryVO;
import com.blog.VO.postVO;
import com.blog.VO.userVO;
@Repository
public interface postDAO extends JpaRepository<postVO,Integer>{
	
	List<postVO> findByuserVO(userVO userVO);
	List<postVO> findBycategoryVO(categoryVO categoryVO);
	
	List<postVO> findByTitleContaining(String title);

}
