package com.example.su.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto{
	
	private Long userId;
	@NotEmpty
	private String userName;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Min(value = 8)
	private String password;
	
	private List<PostDto> posts = new ArrayList<PostDto>();

}
