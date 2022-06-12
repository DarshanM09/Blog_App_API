package com.spring.rest.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	
	private Integer CategoryId;
	
	@NotBlank
	@Size(min = 4,message = "min size should be 4 charachers..")
	private String title;
	@NotBlank
	@Size(min = 2,message = "min sizw ahould be 10charactes...")
	private String discription;


}
