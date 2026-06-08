package com.vikas.linkedin.postsService.service;


import com.vikas.linkedin.postsService.dto.PostCreateRequestDto;
import com.vikas.linkedin.postsService.dto.PostDto;
import com.vikas.linkedin.postsService.entity.Post;
import com.vikas.linkedin.postsService.exception.ResourceNotFoundException;
import com.vikas.linkedin.postsService.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {


    private final PostsRepository postsRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto,Long userId) {
        log.info("Creating post for user with id : {}",userId);
        Post post=modelMapper.map(postCreateRequestDto,Post.class);
        post.setUserId(userId);
        post=postsRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.info("Getting the post with ID: {}",postId);
        Post post=postsRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with ID: "+postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all the posts for the user with Id:{}",userId);
        List<Post> posts=postsRepository.findByUserId(userId);
        return posts
                .stream()
                .map((element)->modelMapper.map(element, PostDto.class))
                .collect(Collectors.toList());

    }
}
