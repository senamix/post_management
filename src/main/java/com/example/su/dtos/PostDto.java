package com.example.su.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long postId;
	@NotEmpty
	private String postName;
	@NotEmpty
	private String content;
	private Long userId;
	private Integer page;
	private Integer limit;
	private Integer totalPage;
	private Integer totalItem;
}
