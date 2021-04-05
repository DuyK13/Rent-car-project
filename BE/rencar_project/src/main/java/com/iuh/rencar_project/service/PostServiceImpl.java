///**
// * @author SangNX4
// * 
// */
//
//package com.iuh.rencar_project.service;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.iuh.rencar_project.dto.request.PostRequestDTO;
//import com.iuh.rencar_project.dto.response.PostResponseDTO;
//import com.iuh.rencar_project.entity.Post;
//import com.iuh.rencar_project.repository.PostRepository;
//import com.iuh.rencar_project.service.template.ICategoryService;
//import com.iuh.rencar_project.service.template.IPostService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class PostServiceImpl implements IPostService {
//	
//    @Autowired
//    private PostRepository postRepository;
//    
//    @Autowired
//    private ICategoryService categoryService;
//
//    @Override
//    public PostResponseDTO savePost(PostRequestDTO postRequestDTO) {
//    	PostResponseDTO result = null;
//    	try {
//    		
//    	}catch(Exception e) {
//    		log
//    	}
//    	
//    	
//        // check case 1
//        if ((post.getCategory() == null)
//                // check case 2
//                || (post.getPost() != null && !post.getCategory().equals(post.getPost().getCategory()))
//                // check case 3
//                || (postRequestDTO.getPostId() != null && post.getPost() == null)) {
//            // return null and do not save post if any of 3 cases above happens.
//            return null;
//        }
//        try {
//            post = postRepository.save(post);
//        } catch (Exception e) {
//            return null;
//        }
//        return post;
//    }
//
//    @Override
//    public boolean deletePostById(Long id) {
//        Boolean result;
//        try {
//            postRepository.deleteById(id);
//            result = true;
//        } catch (Exception e) {
//            result = false;
//        }
//        return result;
//    }
//
//    @Override
//    public PostResponseDTO findPostById(Long id) {
//        Post post = null;
//        Optional<Post> oPost;
//        try {
//            oPost = postRepository.findById(id);
//        } catch (Exception e) {
//            return post;
//        }
//        if (oPost.isPresent()) {
//            post = oPost.get();
//        }
//        return post;
//    }
//
//    /**
//     * This method update a post. The post will not be updated in these 3 cases
//     * below: 1/ Category is null. 2/ Parent post category is different from child
//     * post category. 3/ The parent post ID does not exist.
//     */
//    @Override
//    public PostResponseDTO updatePostById(Long id, PostRequestDTO postRequestDTO) {
//        Post post = findPostById(id);
//        if (post == null) {
//            return post;
//        }
//        post = postModelMapper.convertModelToObject(postRequestDTO, post);
//        // Check case 1.
//        if ((post.getCategory() == null)
//                // Check case 2.
//                || (post.getPost() != null && !post.getCategory().equals(post.getPost().getCategory()))
//                // Check case 3.
//                || (postRequestDTO.getPostId() != null && post.getPost() == null)) {
//            // return null if any of 3 cases above happens.
//            return null;
//        }
//        try {
//            post = postRepository.save(post);
//        } catch (Exception e) {
//            return null;
//        }
//        return post;
//    }
//}
