package com.example.su.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "user")
public class UserEntity{
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(unique = true)
	@NotNull
	private String userName;

	@Column(unique = true)
	@NotNull
	private String email;
	
	@Column
	@NotNull
	private String password;
	
	@OneToMany(mappedBy = "userId")
	private List<PostEntity> posts = new ArrayList<PostEntity>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleName"))
	private List<RoleEntity> roles = new ArrayList<>();

}
