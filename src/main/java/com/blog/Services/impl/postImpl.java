package com.blog.Services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.DAO.categoryDAO;
import com.blog.DAO.postDAO;
import com.blog.DAO.userDAO;
import com.blog.Services.postService;
import com.blog.VO.categoryVO;
import com.blog.VO.postVO;
import com.blog.VO.userVO;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.postDTO;
import com.blog.payloads.postResponse;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class postImpl implements postService {

	@Autowired
	postDAO postDAO;

	@Autowired
	ModelMapper mapper;

	@Autowired
	userDAO userDAO;

	@Autowired
	categoryDAO categoryDAO;

	@Override
	public postDTO createPost(postDTO dto, Integer categoryId, Integer userId) {
		// TODO Auto-generated method stub
		userVO userVO = this.userDAO.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("userVO", "user id", userId));

		categoryVO categoryVO = this.categoryDAO.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("categoryVO", "category id", categoryId));

		postVO postVO = this.mapper.map(dto, postVO.class);
		postVO.setImageName("default.png");
		postVO.setAddedDate(new Date());
		postVO.setUserVO(userVO);
		postVO.setCategoryVO(categoryVO);
		postVO postVO2 = this.postDAO.save(postVO);

		return this.mapper.map(postVO2, postDTO.class);
	}

	@Override
	public postDTO updatePost(postDTO dto, Integer postId) {
		// TODO Auto-generated method stub
		postVO postVO = this.postDAO.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("postVO", "post id", postId));
		postVO.setTitle(dto.getTitle());
		postVO.setContent(dto.getContent());
		postVO.setImageName(dto.getImageName());
		postVO.setAddedDate(dto.getAddedDate());
		postVO postVO2 = this.postDAO.save(postVO);

		return this.mapper.map(postVO2, postDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		postVO postVO = this.postDAO.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("postVO", "post id", postId));
		this.postDAO.delete(postVO);

	}

	@Override
	public postResponse getAllPost(Integer pagenumber, Integer pagesize, String sortBy, String sortdir) {
		// TODO Auto-generated method stub
		org.springframework.data.domain.Sort sort = null;
		if (sortdir.equalsIgnoreCase("asc")) {
			sort = sort.by(sortBy).ascending();
		} else {
			sort = sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
		Page<postVO> pagePost = this.postDAO.findAll(pageable);
		List<postVO> postVOs = pagePost.getContent();
		List<postDTO> nu = postVOs.stream().map(post -> this.mapper.map(post, postDTO.class))
				.collect(Collectors.toList());
		postResponse postResponse = new postResponse();
		postResponse.setContent(nu);
		postResponse.setPagesize(pagePost.getSize());
		postResponse.setPagenumber(pagePost.getNumber());
		postResponse.setTotalelements(pagePost.getTotalElements());
		postResponse.setTotalpage(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public postDTO getPostById(Integer postId) {
		postVO postVO = this.postDAO.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("postVO", "post id", postId));
		return this.mapper.map(postVO, postDTO.class);
	}

	@Override
	public List<postDTO> getPostByCategory(Integer categoryId) {
		categoryVO categoryVO = this.categoryDAO.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("categoryVO", "category id", categoryId));
		List<postVO> postVOs = this.postDAO.findBycategoryVO(categoryVO);
		List<postDTO> dtos = postVOs.stream().map(post -> this.mapper.map(post, postDTO.class))
				.collect(Collectors.toList());
		return dtos;
	}

	@Override
	public List<postDTO> searchPosts(String keyword) {
		List<postVO> postVOs = this.postDAO.findByTitleContaining(keyword);
		List<postDTO> postDTO=postVOs.stream().map((post)->this.mapper.map(post, postDTO.class)).collect(Collectors.toList());
		return postDTO;
	}

	@Override
	public List<postDTO> getPostByUser(Integer userId) {
		userVO userVO = this.userDAO.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("userVO", "user id", userId));
		List<postVO> list = this.postDAO.findByuserVO(userVO);
		List<postDTO> dtos = list.stream().map(post -> this.mapper.map(post, postDTO.class))
				.collect(Collectors.toList());
		System.out.println(dtos.get(0).title);
		return dtos;
	}

}
