package com.spring.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.spring.rest.entity.Post;
import com.spring.rest.payloads.PostDto;
import com.spring.rest.payloads.PostResponse;
import com.spring.rest.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createpost = this.postService.createpost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createpost, HttpStatus.CREATED);

	}

	  @PutMapping("/posts/{postId}") 
	  public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postdto,@PathVariable Integer postId){
		  
		  PostDto updatepost = this.postService.Updatepost(postdto, postId);
		  
	  
	return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
	  
	  
	  
	  
	  
	  }
	
		
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getallPostByuser(@PathVariable Integer userId){
		
		List<PostDto> byUser = this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(byUser,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getallPostByCategories(@PathVariable Integer categoryId){
		
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(postByCategory,HttpStatus.OK);
		
		
	}
	
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deleteBypostId(@PathVariable Integer postId){
		
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Suceefully",true),HttpStatus.OK);
		
	}
	
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getallPost(
			@RequestParam(value="pageNumber", defaultValue="0" ,required=false)Integer pageNumber, 
			@RequestParam(value="pageSize", defaultValue="5" ,required=false)Integer pageSize,
			@RequestParam(value="sortBy", defaultValue="postId" ,required=false)String sortBy,
			@RequestParam(value="sortDir", defaultValue="Asc" ,required=false)String sortDir
			){
		
		
		PostResponse getallPost = this.postService.getallPost(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(getallPost,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId){
		
		PostDto postByid = this.postService.getPostByid(postId);
		
		return new ResponseEntity<PostDto>(postByid,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
