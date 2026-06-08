package com.vikas.linkedin.postsService.service;


import com.vikas.linkedin.postsService.entity.Post;
import com.vikas.linkedin.postsService.entity.PostLike;
import com.vikas.linkedin.postsService.exception.BadRequestException;
import com.vikas.linkedin.postsService.exception.ResourceNotFoundException;
import com.vikas.linkedin.postsService.repository.PostLikeRepository;
import com.vikas.linkedin.postsService.repository.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final ModelMapper modelMapper;
    private final PostsRepository postsRepository;

    @Transactional
    public void likePost(Long postId) {
        Long userId=1l;
        log.info("User with ID:{} liking the post with Id:{}",userId,postId);

        postsRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("No Post with Id: "+postId)
        );
        boolean alreadyhasliked=postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(alreadyhasliked){
            throw new BadRequestException("Cannot like the post again");
        }
        PostLike postLike=new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

        // TODO : send notifications to the owner of the post

    }

    @Transactional
    public void unlikePost(Long postId) {
        Long userId=1l;
        log.info("User with ID:{} unliking the post with Id:{}",userId,postId);
        postsRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("No Post with Id: "+postId)
        );
        boolean alreadyhasliked=postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(!alreadyhasliked){
            throw new BadRequestException("Cannot unlike the post that you have not liked");
        }
        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
    }
}
