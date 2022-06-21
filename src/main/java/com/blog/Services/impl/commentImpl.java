package com.blog.Services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Controllers.postController;
import com.blog.DAO.commentDAO;
import com.blog.DAO.postDAO;
import com.blog.Services.commentService;
import com.blog.VO.commentVO;
import com.blog.VO.postVO;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.commentDTO;
import com.blog.payloads.postDTO;

@Service
public class commentImpl implements commentService {
	@Autowired postDAO dao;
	@Autowired commentDAO commentDAO;
	@Autowired ModelMapper mapper;

	@Override
	public commentDTO addComment(commentDTO commentDTO, int postId) {
	postVO postVO=this.dao.findById(postId).orElseThrow(() -> new ResourceNotFoundException("postVO", "post id", postId));
	commentVO commentVO=this.mapper.map(commentDTO,commentVO.class);
	commentVO.setPostVO(postVO);
	commentVO commentVO2=this.commentDAO.save(commentVO);
		return this.mapper.map(commentVO2, commentDTO.class);
	}

	@Override
	public void deleteComment(int commentId) {
	commentVO commentVO=this.commentDAO.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("commentVO", "comment id", commentId));
	this.commentDAO.delete(commentVO);

	}

}
