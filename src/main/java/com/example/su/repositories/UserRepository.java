package com.example.su.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.su.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	//method of spring data jpa
    UserEntity findByUserName(String username);
    UserEntity findByEmail(String email);
}
