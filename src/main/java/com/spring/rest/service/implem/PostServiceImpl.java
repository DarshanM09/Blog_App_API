package com.spring.rest.service.implem;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.rest.Repository.CategoryRepo;
import com.spring.rest.Repository.PostRepo;
import com.spring.rest.Repository.UserRepo;
import com.spring.rest.entity.Category;
import com.spring.rest.entity.Post;
import com.spring.rest.entity.User;
import com.spring.rest.exceptions.ResourceNotFoundException;
import com.spring.rest.payloads.PostDto;
import com.spring.rest.payloads.PostResponse;
import com.spring.rest.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo Urepo;

	@Autowired
	private CategoryRepo CRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createpost(PostDto postdto, Integer userId, Integer categoryId) {

		User user = this.Urepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category category = this.CRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		Post post = this.dtoTopost(postdto);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post postSaved = this.postRepo.save(post);

		PostDto postTodto = this.postTodto(postSaved);

		return postTodto;
	}

	@Override
	public PostDto Updatepost(PostDto postdto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postId));

		post.setPostTitle(post.getPostTitle());
		post.setContent(postdto.getContent());
		post.setImageName("default.png");
	
	

		Post post2 = postRepo.save(post);

		PostDto postDto2 = this.postTodto(post2);

		return postDto2;
	}

	@Override
	public void deletePost(Integer postId) {
		

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postId));
		
		
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getallPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
	Sort sort; 
		if(sortDir.equalsIgnoreCase("desc")) {
			sort=Sort.by(sortBy).descending();
		}else {
			sort=Sort.by(sortBy).ascending();
		}
		
	   org.springframework.data.domain.Pageable p=PageRequest.of(pageNumber, pageSize, sort);
	

		Page<Post> findAll = this.postRepo.findAll(p);
		
		List<Post> content = findAll.getContent();
		
		List<PostDto> collect = content.stream().map(list1 ->this.postTodto(list1)).collect(Collectors.toList());
		
		PostResponse postresponse=new PostResponse();
		postresponse.setContent(collect);
		postresponse.setPageNumber(findAll.getNumber());
		postresponse.setPageSize(findAll.getSize());
		postresponse.setTotalElement(findAll.getTotalElements());
		postresponse.setTotlaPages(findAll.getTotalPages());
		postresponse.setLastPage(findAll.isLast());
		
		return postresponse;
	}

	@Override
	public PostDto getPostByid(Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		
	
		return this.postTodto(post);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category category = this.CRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		List<Post> categoryList = this.postRepo.findByCategory(category);

		List<PostDto> collect = categoryList.stream().map(cat -> this.postTodto(cat)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {

		User user = this.Urepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		List<Post> userList = this.postRepo.findByUser(user);
		List<PostDto> collect = userList.stream().map(user1 -> this.postTodto(user1)).collect(Collectors.toList());

		return collect;
	}

	public Post dtoTopost(PostDto postDto) {
		Post posts = modelMapper.map(postDto, Post.class);

		return posts;

	}

	public PostDto postTodto(Post post) {

		PostDto postDto = modelMapper.map(post, PostDto.class);

		return postDto;
	}

}
