package com.spring.rest.Controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.payloads.CategoryDto;
import com.spring.rest.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto ){
		
		CategoryDto addcategory = categoryService.addcategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(addcategory,HttpStatus.CREATED);
	}

	
	//update-category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> Update(@Valid @RequestBody CategoryDto categorydto,@PathVariable Integer categoryId){
		
		CategoryDto updatecategory = categoryService.Updatecategory(categorydto, categoryId);
		
		return new ResponseEntity<CategoryDto>(updatecategory,HttpStatus.OK);
	}
	
	//delete-category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCat(@PathVariable Integer categoryId){
		
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted Suceesfully",true),HttpStatus.OK);
	}
	
	//getall categories
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		
		List<CategoryDto> list = categoryService.getallCategories();
		
		return new ResponseEntity<List<CategoryDto>>(list,HttpStatus.OK);
		
	}
	
	
	//get single category
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSinglecat(@PathVariable Integer categoryId)
	{
		CategoryDto getSinglecategory = categoryService.GetSinglecategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(getSinglecategory,HttpStatus.OK);
	}
	
	
	
	
}

