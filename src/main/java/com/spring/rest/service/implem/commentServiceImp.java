package com.spring.rest.service.implem;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.Repository.CommentRepo;
import com.spring.rest.Repository.PostRepo;
import com.spring.rest.entity.Comment;
import com.spring.rest.entity.Post;
import com.spring.rest.exceptions.ResourceNotFoundException;
import com.spring.rest.payloads.CommentDto;
import com.spring.rest.payloads.PostDto;
import com.spring.rest.service.CommentService;
@Service
public class commentServiceImp implements CommentService{

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto create(CommentDto commentDto, Integer PostId) {
	
		Post post = this.postRepo.findById(PostId).orElseThrow(() ->new ResourceNotFoundException("post", "postId", PostId));
		
		Comment comment = this.dtoTocomment(commentDto);
		
		comment.setPost(post);
		
		Comment save = commentRepo.save(comment);
		
		CommentDto commentTodto = this.commentTodto(save);
		
		return commentTodto;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(() ->new ResourceNotFoundException("comment", "commentId", commentId));
		
		this.commentRepo.delete(comment);
		
	}

	
	
	
	public Comment dtoTocomment(CommentDto commentDto) {
		 Comment comment = modelMapper.map(commentDto, Comment.class);

		return comment;

	}

	public CommentDto commentTodto(Comment comment) {

		  CommentDto commentDto = modelMapper.map(comment, CommentDto.class);

		return commentDto;
	}
	
	
}
