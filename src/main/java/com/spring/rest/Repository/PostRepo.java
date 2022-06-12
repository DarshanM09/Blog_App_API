package com.spring.rest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.entity.Category;
import com.spring.rest.entity.Post;
import com.spring.rest.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	

}
