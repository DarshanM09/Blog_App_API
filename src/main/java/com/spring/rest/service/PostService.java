package com.spring.rest.service;

import java.util.List;

import com.spring.rest.entity.Post;
import com.spring.rest.payloads.PostDto;
import com.spring.rest.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createpost(PostDto postdto,Integer userId,Integer categoryId);
	
	
	//update
	PostDto Updatepost(PostDto postdto,Integer postId);
	
	
	//delete
	void deletePost(Integer postId);
	
	
	//get all post
	PostResponse getallPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	
	//get single post
	PostDto getPostByid(Integer postId);
	
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	



}
