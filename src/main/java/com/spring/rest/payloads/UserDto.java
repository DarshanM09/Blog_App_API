package com.spring.rest.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 3,message = "Username must be minimum 3 characters")
	private String name;
	
	@Email(message = "Email should not be empty")
	private String email;
	
	@NotEmpty
	@Size(min = 4,message = "password must be minimum 4 characters")
	private String password;

	@NotNull
	private String about;
	
}
