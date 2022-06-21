package com.blog.Services;

import com.blog.payloads.commentDTO;

public interface commentService {
	
	commentDTO addComment(commentDTO commentDTO,int postId);
	void deleteComment(int commentId);

}
