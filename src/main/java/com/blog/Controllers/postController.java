package com.blog.Controllers;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.Config.appConstants;
import com.blog.Services.fileService;
import com.blog.Services.postService;
import com.blog.VO.postVO;
import com.blog.payloads.apiResponse;
import com.blog.payloads.postDTO;
import com.blog.payloads.postResponse;

@RestController
@RequestMapping("/api")
public class postController {

	@Autowired
	postService postService;

	@Autowired
	fileService fileService;
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<postDTO> createPost(@RequestBody postDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		postDTO dto = this.postService.createPost(postDTO, categoryId, userId);
		return new ResponseEntity<postDTO>(dto, HttpStatus.CREATED);

	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<postDTO>> getPostsByUsers(@PathVariable Integer userId) {
		List<postDTO> dto = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<postDTO>>(dto, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<postDTO>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<postDTO> dto = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<postDTO>>(dto, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<postResponse> getAllPosts(
			@RequestParam(value = "pagenumber", defaultValue = appConstants.page_number, required = false) Integer pagenumber,
			@RequestParam(value = "pagesize", defaultValue = appConstants.page_size, required = false) Integer pagesize,
			@RequestParam(value = "sortBy", defaultValue = appConstants.sort_by, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = appConstants.sort_dir, required = false) String sortdir) {
		postResponse dto = this.postService.getAllPost(pagenumber, pagesize, sortBy, sortdir);
		return new ResponseEntity<postResponse>(dto, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<postDTO> getPostById(@PathVariable Integer postId) {
		postDTO dto = this.postService.getPostById(postId);
		return new ResponseEntity<postDTO>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public apiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new apiResponse("successfullt deleted", false);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<postDTO> updatePost(@RequestBody postDTO dto, @PathVariable Integer postId) {
		postDTO dto2 = this.postService.updatePost(dto, postId);
		return new ResponseEntity<postDTO>(dto2, HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<postDTO>> search(@PathVariable("keywords") String keywords) {
		List<postDTO> dtos = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<postDTO>>(dtos, HttpStatus.OK);

	}

	@PostMapping("/post/image/upload/{postId}")
	private ResponseEntity<postDTO> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		postDTO dto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);

		dto.setImageName(fileName);
		postDTO postDTO = this.postService.updatePost(dto, postId);
		return new ResponseEntity<postDTO>(postDTO, HttpStatus.OK);
	}

	@GetMapping(value = "post/image/{imagename}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void downloading(@PathVariable("imagename") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream reInputStream = this.fileService.getResource(path, imageName);
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(reInputStream, response.getOutputStream());
	}

}
