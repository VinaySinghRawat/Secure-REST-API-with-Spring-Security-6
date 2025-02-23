package com.project.SecurityApp.SecurityApplication.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.SecurityApp.SecurityApplication.dto.PostDTO;
import com.project.SecurityApp.SecurityApplication.entities.PostEntity;
import com.project.SecurityApp.SecurityApplication.entities.User;
import com.project.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.project.SecurityApp.SecurityApplication.repositories.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	@Override
	public List<PostDTO> getAllPosts() {
		// TODO Auto-generated method stub
		return postRepository.findAll().stream().map(postEntity->modelMapper.map(postEntity, PostDTO.class)).toList();
	}

	@Override
	public PostDTO createNewPost(PostDTO inputPostDTO) {
		// TODO Auto-generated method stub
//			PostEntity postEntity =modelMapper.map(inputPostDTO, PostEntity.class);
//	return	modelMapper.map(postRepository.save(postEntity),PostDTO.class);
//			
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity postEntity = modelMapper.map(inputPostDTO, PostEntity.class);
        postEntity.setAuthor(user);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
		
	}

	@Override
	public PostDTO getPostById(Long postId) {
		// TODO Auto-generated method stub
//	User user=(User)	SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
//	log.info("user {}",user);
	
	
	PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found with id "+postId));
	return modelMapper.map(postEntity,PostDTO.class);
		
	}

	@Override
	public PostDTO updatePost(PostDTO inputPost, Long postId) {
		// TODO Auto-generated method stub
		PostEntity olderPost=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post is not available with id: "+postId));
		inputPost.setId(postId);
        modelMapper.map(inputPost, olderPost);
        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDTO.class);
	}

}
