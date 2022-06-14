package com.spring.rest.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.spring.rest.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	
	private Integer postId;
	private String PostTitle;
	
	private String Content;
	
	private String ImageName;
	private Date addedDate;
	
	private UserDto user;
	private CategoryDto category;
	
	private Set<Comment> comments=new HashSet<>();
	

}
