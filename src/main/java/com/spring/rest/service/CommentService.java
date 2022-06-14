package com.spring.rest.service;

import com.spring.rest.payloads.CommentDto;

public interface CommentService {
	
	CommentDto create(CommentDto commentDto,Integer PostId);
	
	
	void deleteComment(Integer commentId);

}
