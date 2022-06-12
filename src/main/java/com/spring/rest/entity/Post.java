package com.spring.rest.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Post {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	private String PostTitle;
	private String Content;
	private String ImageName;
	private Date addedDate;
	
	
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
}
