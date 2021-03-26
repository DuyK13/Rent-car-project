package com.iuh.rencarproject.ultis.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iuh.rencarproject.dto.request.PostRequestDTO;
import com.iuh.rencarproject.entity.Post;
import com.iuh.rencarproject.service.template.ICategoryService;
import com.iuh.rencarproject.service.template.IPostService;
import com.iuh.rencarproject.service.template.IUserService;

@Component
public class PostRequestMapper implements IModelMapper<Post, PostRequestDTO> {
	
    @Autowired
    IUserService userService;
    
    @Autowired
    ICategoryService categoryService;
    
    @Autowired
    IPostService postService;

    @Override
    public Post convertModelToObject(PostRequestDTO requestPostDTO, Post post) {
        post.setTitle(requestPostDTO.getTitle());
        post.setShortTitle(requestPostDTO.getShortTitle());
        post.setContent(requestPostDTO.getContent());
        post.setView(post.getView() == null ? 0L : post.getView());
        post.setCategory(categoryService.findCategoryByName(requestPostDTO.getCategory()));
        post.setUser(userService.findByUserName(requestPostDTO.getUserName()));
        post.setPost(postService.findPostById(requestPostDTO.getPostId()));
        return post;
    }

    @Override
    public List<Post> convertListModelToListObject(List<PostRequestDTO> ms) {
        // TODO Auto-generated method stub
        return null;
    }

}
