package com.example.su.services;

import com.example.su.dtos.UserDto;
import com.example.su.exceptions.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
	//CRUD
	UserDto findById(Long id);
	UserDto findByUserName(String userName);
	UserDto save(UserDto user);
	void delete(Long[] ids) throws ResourceNotFoundException;
}
