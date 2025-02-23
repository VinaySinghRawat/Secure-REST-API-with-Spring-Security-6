package com.project.SecurityApp.SecurityApplication.services;

import java.util.List;


import com.project.SecurityApp.SecurityApplication.dto.PostDTO;



public interface PostService {

	List<PostDTO> getAllPosts();
	PostDTO createNewPost(PostDTO inputPostDTO);
	PostDTO getPostById(Long postId);
	PostDTO updatePost(PostDTO inputPost, Long postId);
}
