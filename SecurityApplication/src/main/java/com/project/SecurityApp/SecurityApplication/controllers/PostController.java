package com.project.SecurityApp.SecurityApplication.controllers;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.SecurityApp.SecurityApplication.dto.PostDTO;
import com.project.SecurityApp.SecurityApplication.services.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {
	
private final PostService postService;
@GetMapping
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public List<PostDTO> getAllPosts(){
	return postService.getAllPosts();
}

@GetMapping("/{postId}")
@PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
public PostDTO getPostById(@PathVariable Long postId) {
	return postService.getPostById(postId);
}

@PostMapping
public PostDTO creatNewPost(@RequestBody PostDTO inputPost) {
	return postService.createNewPost(inputPost);
}
@PutMapping("/{postId}")
public PostDTO updatePost(@RequestBody PostDTO inputPost,@PathVariable Long postId) {
	return postService.updatePost(inputPost,postId);
}
}
