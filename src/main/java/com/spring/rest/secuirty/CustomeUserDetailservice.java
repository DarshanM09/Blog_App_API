package com.spring.rest.secuirty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.rest.Repository.UserRepo;
import com.spring.rest.entity.User;
import com.spring.rest.exceptions.ResourceNotFoundException;

@Service
public class CustomeUserDetailservice implements UserDetailsService{

	
	@Autowired
	private UserRepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		User user = this.userrepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user", "email"+username, 0));
		
		return user;
	}

}
