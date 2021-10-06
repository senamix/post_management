package com.example.su.repositories;

import com.example.su.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
    List<PostEntity> findByPostNameContains(String postName);
    PostEntity findByPostNameAndContent(String postName, String content);
    Page<PostEntity> findAll(Pageable pageable);
}
