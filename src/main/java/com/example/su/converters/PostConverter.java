package com.example.su.converters;

import com.example.su.dtos.PostDto;
import com.example.su.entities.PostEntity;
import com.example.su.entities.UserEntity;
import com.example.su.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostConverter {

	@Autowired ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;

	public PostDto toDto(PostEntity entity) {
		PostDto dto = new PostDto();
		dto.setPostId(entity.getPostId());
		dto.setPostName(entity.getPostName());
		dto.setContent(entity.getContent());
		dto.setUserId(entity.getUserId().getUserId());
		return dto;
	}

	public PostEntity toEntity(PostDto dto) {
		PostEntity entity = new PostEntity();
		Optional<UserEntity> userEntity = userRepository.findById(dto.getUserId());
		entity.setPostId(dto.getPostId());
		entity.setPostName(dto.getPostName());
		entity.setContent(dto.getContent());
		entity.setUserId(userEntity.get());
		return entity;
	}
}
