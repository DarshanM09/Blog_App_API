package com.spring.rest.service.implem;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.Repository.UserRepo;
import com.spring.rest.entity.User;
import com.spring.rest.exceptions.ResourceNotFoundException;
import com.spring.rest.payloads.UserDto;
import com.spring.rest.service.userService;

@Service
public class userServiceImple implements userService {

	@Autowired
	private UserRepo Urepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addUser(UserDto userdto) {

		// converting dto object to user
		User user = this.dtoToUser(userdto);

		User users = Urepo.save(user);

		// user to dto
		return this.userTodto(users);
	}

	@Override
	public UserDto updateuser(UserDto userdto, Integer Userid) {

		User user = this.Urepo.findById(Userid).orElseThrow(() -> new ResourceNotFoundException("User", "Id", Userid));

		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());

		User save = this.Urepo.save(user);

		UserDto userdto1 = this.userTodto(save);

		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer UserId) {

		User user = this.Urepo.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", UserId));
		


		
		return this.userTodto(user);
	}

	@Override
	public List<UserDto> getallUser() {


		List<User> users = this.Urepo.findAll();
		List<UserDto> users1 = users.stream().map(user ->this.userTodto(user)).collect(Collectors.toList());
		
		return users1;
	}

	@Override
	public void deleteUser(Integer Userid) {


		User user = this.Urepo.findById(Userid).orElseThrow(() -> new ResourceNotFoundException("User", "Id", Userid));
		
		this.Urepo.delete(user);
		

	}

	private User dtoToUser(UserDto userdto) {

		User user =modelMapper.map(userdto, User.class);
		
		
		
		
		//traditional way
		/*
		 * user.setId(userdto.getId()); user.setName(userdto.getName());
		 * user.setEmail(userdto.getEmail()); user.setPassword(userdto.getPassword());
		 * user.setAbout(userdto.getAbout());
		 */

		return user;
	}

	private UserDto userTodto(User user) {

		UserDto userdto =modelMapper.map(user, UserDto.class);
	
		
		/*
		 * userdto.setId(user.getId()); userdto.setName(user.getName());
		 * userdto.setEmail(user.getEmail()); userdto.setPassword(user.getPassword());
		 * userdto.setAbout(user.getAbout());
		 */
		return userdto;
	}

}
