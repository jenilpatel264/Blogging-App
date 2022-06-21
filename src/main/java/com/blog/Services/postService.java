package com.blog.Services;

import java.util.List;

import com.blog.payloads.postDTO;
import com.blog.payloads.postResponse;

public interface postService {

	postDTO createPost(postDTO dto,Integer categoryId,Integer userId);

	postDTO updatePost(postDTO dto, Integer postId);

	void deletePost(Integer postId);

	postResponse getAllPost(Integer pagenumber,Integer pagesize,String sortBy,String sortdir);

	postDTO getPostById(Integer postId);

	List<postDTO> getPostByCategory(Integer categoryId);
	
	List<postDTO> searchPosts(String keyword);

	List<postDTO> getPostByUser(Integer userId);

}
