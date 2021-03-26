package com.iuh.rencarproject.service.template;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.iuh.rencarproject.dto.request.PostRequestDTO;
import com.iuh.rencarproject.dto.response.AppResponse;
import com.iuh.rencarproject.entity.Post;

public interface IPostService {
	
    List<Post> findAllPostByCategory(String categoryName);

    List<Post> findLastestPostByCategory(String categoryName);

    Post savePost(PostRequestDTO postRequestDTO);

    boolean deletePostById(Long id);

    Post findPostById(Long id);

    Post updatePostById(Long id, PostRequestDTO postRequestDTO);

    ResponseEntity<AppResponse> responseSavePost(PostRequestDTO postRequestDTO);

    ResponseEntity<AppResponse> responseDeletePost(Long id);

    ResponseEntity<AppResponse> responsefindPostById(Long id);

    ResponseEntity<AppResponse> responseUpdatePostById(Long id, PostRequestDTO postRequestDTO);

    ResponseEntity<AppResponse> responseFindAllPostByCategory(String categoryName);

    ResponseEntity<AppResponse> responseFindLastestPostByCategory(String categoryName);
}
