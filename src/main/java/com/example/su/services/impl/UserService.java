package com.example.su.services.impl;

import com.example.su.converters.UserConverter;
import com.example.su.dtos.UserDto;
import com.example.su.entities.RoleEntity;
import com.example.su.entities.UserEntity;
import com.example.su.exceptions.ResourceNotFoundException;
import com.example.su.repositories.UserRepository;
import com.example.su.security.CustomUserDetails;
import com.example.su.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService {

	@Autowired UserRepository userRepository;

	@Autowired UserConverter userConverter;
	
	@Override
	public UserDto findById(Long id){
		UserEntity entity = userRepository.findById(id).get();
		if(entity != null){
			return userConverter.toDto(entity);
		}
		return null;
	}

	@Override
	public UserDto findByUserName(String userName){
		UserEntity entity = userRepository.findByUserName(userName);

		if(entity != null){
			return userConverter.toDto(entity);
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(Long[] ids) throws ResourceNotFoundException {
		for (Long id : ids) {
			UserEntity entity = userRepository.findById(id).get();
			if (entity != null) {
				userRepository.delete(entity);
			} else {
				throw new ResourceNotFoundException("User id = " + id);
			}
		}
	}

	@Transactional
	@Override
	public UserDto save(UserDto userDto) {
		UserEntity entity = userConverter.toEntity(userDto);
		if(userRepository.findByEmail(entity.getEmail()) == null){
			if(userRepository.findByUserName(entity.getUserName()) == null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				entity.setPassword(passwordEncoder.encode(entity.getPassword()));
				RoleEntity role = new RoleEntity("USER");
				List<RoleEntity> roles = new ArrayList<RoleEntity>();
				roles.add(role);
				entity.setRoles(roles);
				userRepository.save(entity);
				return userConverter.toDto(entity);
			}
		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUserName(userName);
		if(userEntity == null) {
			throw new UsernameNotFoundException("username not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()){
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		//can custom the UserDetails here
		CustomUserDetails myUser = new CustomUserDetails(userEntity.getUserName(),userEntity.getPassword(),authorities);
		myUser.setUserId(userEntity.getUserId());
		myUser.setEmail(userEntity.getEmail());

		return myUser;
	}
}
