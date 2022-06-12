package com.spring.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>  {

}
