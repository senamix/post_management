package com.example.su.services;

import com.example.su.dtos.PostDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
	//CRUD
	List<PostDto> findAll();
	List<PostDto> findAll(Pageable pageable);
	long totalPost();
	List<PostDto> findByPostNameContains(String postName);
	PostDto findByPostNameAndContent(PostDto postDto);
	PostDto findById(Long id);
	PostDto save(PostDto post);
	void delete(Long postId);
}
