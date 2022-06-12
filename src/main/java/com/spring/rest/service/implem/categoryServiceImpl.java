package com.spring.rest.service.implem;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.Repository.CategoryRepo;
import com.spring.rest.entity.Category;
import com.spring.rest.entity.User;
import com.spring.rest.exceptions.ResourceNotFoundException;
import com.spring.rest.payloads.CategoryDto;
import com.spring.rest.service.CategoryService;

@Service
public class categoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo CRepo;

	@Autowired
	private ModelMapper modelMapper;

	// create-category

	@Override
	public CategoryDto addcategory(CategoryDto categorydto) {

		Category cat = this.DtoToCat(categorydto);

		Category save = CRepo.save(cat);

		return this.catToDto(save);
	}

	@Override
	public CategoryDto Updatecategory(CategoryDto categorydto, Integer categoryId) {

		Category category = this.CRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		category.setTitle(categorydto.getTitle());
		category.setDiscription(categorydto.getDiscription());

		Category save = CRepo.save(category);

		CategoryDto categoryDto2 = this.catToDto(category);

		return categoryDto2;
	}

	@Override
	public CategoryDto GetSinglecategory(Integer categoryId) {

		Category category = this.CRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", categoryId));

		return this.catToDto(category);
	}

	@Override
	public List<CategoryDto> getallCategories() {

		List<Category> list = CRepo.findAll();

		List<CategoryDto> collect = list.stream().map(list1 -> this.catToDto(list1)).collect(Collectors.toList());

		return collect;
	}
	
	

	@Override
	public void deleteCategory(Integer categoryId) {

		Category category = this.CRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", categoryId));

		this.CRepo.delete(category);
	}

	
	
	public Category DtoToCat(CategoryDto categoryDto) {

		Category category = modelMapper.map(categoryDto, Category.class);

		return category;
	}

	public CategoryDto catToDto(Category category) {

		CategoryDto categoryDto1 = modelMapper.map(category, CategoryDto.class);

		return categoryDto1;
	}

}
