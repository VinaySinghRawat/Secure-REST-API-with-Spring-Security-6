package com.project.SecurityApp.SecurityApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SecurityApp.SecurityApplication.entities.PostEntity;


@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

}
