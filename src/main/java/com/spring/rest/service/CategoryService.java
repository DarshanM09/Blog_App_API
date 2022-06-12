package com.spring.rest.service;

import java.util.List;

import com.spring.rest.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto addcategory(CategoryDto categorydto);
	
	
	CategoryDto Updatecategory(CategoryDto categorydto,Integer categoryId);
	
	
	CategoryDto GetSinglecategory(Integer categoryId);
	
	
	List<CategoryDto> getallCategories();
	
	
	void deleteCategory(Integer categoryId);

}
