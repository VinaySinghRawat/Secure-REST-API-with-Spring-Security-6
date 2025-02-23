package com.project.SecurityApp.SecurityApplication.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.project.SecurityApp.SecurityApplication.dto.PostDTO;
import com.project.SecurityApp.SecurityApplication.entities.User;
import com.project.SecurityApp.SecurityApplication.services.PostService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostSecurity {
	private  final PostService postService;

    public boolean isOwnerOfPost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         PostDTO post = postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());
    }
}
