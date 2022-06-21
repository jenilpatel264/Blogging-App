package com.blog.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Services.commentService;
import com.blog.payloads.apiResponse;
import com.blog.payloads.commentDTO;

@RestController
@RequestMapping("/api")
public class commentController {
	@Autowired commentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<commentDTO> addComment(@RequestBody commentDTO commentDTO,@PathVariable int postId)
	{
		commentDTO commentDTO2=this.commentService.addComment(commentDTO, postId);
		return new ResponseEntity<commentDTO>(commentDTO2,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<apiResponse> deleteComment(@PathVariable int commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<apiResponse>(new apiResponse("successfully deleted", true),HttpStatus.CREATED);
	}
	

}
