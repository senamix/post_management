package com.example.su.services.impl;

import com.example.su.converters.PostConverter;
import com.example.su.converters.UserConverter;
import com.example.su.dtos.PostDto;
import com.example.su.entities.PostEntity;
import com.example.su.repositories.PostRepository;
import com.example.su.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostConverter postConverter;

	@Autowired
	UserConverter userConverter;

	@Override
	public List<PostDto> findAll() {
		List<PostEntity> postEntities = postRepository.findAll();
		List<PostDto> postDtos = new ArrayList<>();
		for(PostEntity entity : postEntities){
			postDtos.add(postConverter.toDto(entity));
		}
		return postDtos;
	}

	@Override
	public List<PostDto> findAll(Pageable pageable) {
		List<PostDto> postDtos = new ArrayList<>();
		for(PostEntity entity : postRepository.findAll(pageable)) {
			postDtos.add(postConverter.toDto(entity));
		}
		return postDtos;
	}

	@Override
	public long totalPost() {
		return postRepository.count();
	}

	@Override
	public List<PostDto> findByPostNameContains(String postName) {
		List<PostEntity> entities = postRepository.findByPostNameContains(postName);
		List<PostDto> dtos = new ArrayList<>();
		if(entities != null){
			for (PostEntity entity : entities){
				dtos.add(postConverter.toDto(entity));
			}
			return dtos;
		}
		return null;
	}

	@Override
	public PostDto findByPostNameAndContent(PostDto postDto) {
		PostEntity entity = postRepository.findByPostNameAndContent(postDto.getPostName(),postDto.getContent());
		if(entity != null){
			return postConverter.toDto(entity);
		}
		return null;
	}

	@Override
	public PostDto findById(Long id){
		PostEntity entity = postRepository.findById(id).get();
		if(entity != null){
			return postConverter.toDto(entity);
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public PostDto save(PostDto postDto) {
		PostEntity entity = postConverter.toEntity(postDto);
		if(postRepository.findByPostNameAndContent(entity.getPostName(), entity.getContent()) == null){
			postRepository.save(entity);
			return postConverter.toDto(entity);
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(Long postId){
		PostEntity entity = postRepository.findById(postId).get();
		if (entity != null) {
			postRepository.delete(entity);
		}
	}
}
