package com.example.su.converters;

import com.example.su.dtos.UserDto;
import com.example.su.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

	@Autowired ModelMapper modelMapper;

	public UserDto toDto(UserEntity entity) {
		UserDto dto = modelMapper.map(entity,UserDto.class);
		return dto;
	}

	public UserEntity toEntity(UserDto dto) {
		UserEntity entity = modelMapper.map(dto,UserEntity.class);
		return entity;
	}
}
