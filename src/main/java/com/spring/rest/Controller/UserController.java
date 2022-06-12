package com.spring.rest.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.payloads.UserDto;
import com.spring.rest.service.userService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private userService userservice;
	
	//post-create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto){
		UserDto addUser = this.userservice.addUser(userdto);
		
		return new ResponseEntity<UserDto>(addUser,HttpStatus.CREATED);
	}
	
	//update-user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> UpdateUser(@Valid @RequestBody UserDto userdto,@PathVariable Integer userId){
		
		UserDto updatdeuser = userservice.updateuser(userdto, userId);
		return new ResponseEntity<UserDto>(updatdeuser,HttpStatus.OK);
		
	}
	
	
	//delete-user
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		
		userservice.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Suceefully",true),HttpStatus.OK);
	}
	
	//get User
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> GetSingleUser(@PathVariable Integer userId){
		
		UserDto userById = userservice.getUserById(userId);
		return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
	}
	
	//get all Users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>GetAllUsers(){
		
		List<UserDto> getallUser = userservice.getallUser();
		return new ResponseEntity<List<UserDto>>(getallUser,HttpStatus.OK);
		
	}
	
	
}
