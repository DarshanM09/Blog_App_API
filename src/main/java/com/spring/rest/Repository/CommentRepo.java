package com.spring.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
