package com.spring.rest.service;

import java.util.List;

import com.spring.rest.payloads.UserDto;

public interface userService {

	
	UserDto addUser(UserDto user);
	UserDto updateuser(UserDto user,Integer Userid);
	UserDto getUserById(Integer UserId);
	List<UserDto> getallUser();
	void deleteUser(Integer Userid);
	
}
