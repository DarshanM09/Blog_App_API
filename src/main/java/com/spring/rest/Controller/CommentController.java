package com.spring.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.payloads.CommentDto;
import com.spring.rest.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		
		CommentDto create = this.commentService.create(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(create,HttpStatus.CREATED);
		
		
	}
	
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Sucessfully..!!",true),HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
}
