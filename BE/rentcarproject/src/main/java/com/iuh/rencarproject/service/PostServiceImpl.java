/**
 * @author SangNX4
 * 
 */

package com.iuh.rencarproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iuh.rencarproject.dto.request.PostRequestDTO;
import com.iuh.rencarproject.dto.response.AppResponse;
import com.iuh.rencarproject.dto.response.ErrorResponse;
import com.iuh.rencarproject.dto.response.PostResponseDTO;
import com.iuh.rencarproject.dto.response.ResponseDTO;
import com.iuh.rencarproject.dto.response.SuccessResponse;
import com.iuh.rencarproject.entity.Category;
import com.iuh.rencarproject.entity.Post;
import com.iuh.rencarproject.repotitory.PostRepository;
import com.iuh.rencarproject.service.template.ICategoryService;
import com.iuh.rencarproject.service.template.IPostService;
import com.iuh.rencarproject.ultis.mapper.IModelMapper;

@Service
public class PostServiceImpl implements IPostService {
	
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private IModelMapper<Post, PostRequestDTO> postModelMapper;
    
    @Autowired
    private ICategoryService categoryService;

    /**
     * This save a new post into database. The post will not be saved in these 3
     * cases below: 1/ Category is null. 2/ Parent post category is different from
     * child post category. 3/ The parent post ID does not exist.
     */
    @Override
    public Post savePost(PostRequestDTO postRequestDTO) {
        Post post = postModelMapper.convertModelToObject(postRequestDTO, new Post());
        // check case 1
        if ((post.getCategory() == null)
                // check case 2
                || (post.getPost() != null && !post.getCategory().equals(post.getPost().getCategory()))
                // check case 3
                || (postRequestDTO.getPostId() != null && post.getPost() == null)) {
            // return null and do not save post if any of 3 cases above happens.
            return null;
        }
        try {
            post = postRepository.save(post);
        } catch (Exception e) {
            return null;
        }
        return post;
    }

    @Override
    public boolean deletePostById(Long id) {
        Boolean result;
        try {
            postRepository.deleteById(id);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public Post findPostById(Long id) {
        Post post = null;
        Optional<Post> oPost;
        try {
            oPost = postRepository.findById(id);
        } catch (Exception e) {
            return post;
        }
        if (oPost.isPresent()) {
            post = oPost.get();
        }
        return post;
    }

    /**
     * This method update a post. The post will not be updated in these 3 cases
     * below: 1/ Category is null. 2/ Parent post category is different from child
     * post category. 3/ The parent post ID does not exist.
     */
    @Override
    public Post updatePostById(Long id, PostRequestDTO postRequestDTO) {
        Post post = findPostById(id);
        if (post == null) {
            return post;
        }
        post = postModelMapper.convertModelToObject(postRequestDTO, post);
        // Check case 1.
        if ((post.getCategory() == null)
                // Check case 2.
                || (post.getPost() != null && !post.getCategory().equals(post.getPost().getCategory()))
                // Check case 3.
                || (postRequestDTO.getPostId() != null && post.getPost() == null)) {
            // return null if any of 3 cases above happens.
            return null;
        }
        try {
            post = postRepository.save(post);
        } catch (Exception e) {
            return null;
        }
        return post;
    }

    @Override
    public ResponseEntity<AppResponse> responseSavePost(PostRequestDTO postRequestDTO) {
        AppResponse response;
        Post post = savePost(postRequestDTO);
        if (post != null) {
            response = new SuccessResponse<ResponseDTO>("Post creation succeeds.", new PostResponseDTO(post));
        } else {
            response = new ErrorResponse("Post creation fails.");
        }
        return new ResponseEntity<>(response, response.getSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AppResponse> responseDeletePost(Long id) {
        boolean deleteState = deletePostById(id);
        AppResponse response;
        if (deleteState) {
            response = new SuccessResponse<ResponseDTO>("Deleted", null);
        } else {
            response = new ErrorResponse("Post not found.");
        }
        return new ResponseEntity<>(response, response.getSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<AppResponse> responsefindPostById(Long id) {
        AppResponse response;
        Post post = findPostById(id);
        if (post != null) {
            response = new SuccessResponse<ResponseDTO>("Successfully.", new PostResponseDTO(post));
        } else {
            response = new ErrorResponse("Post not found");
        }
        return new ResponseEntity<>(response, response.getSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<AppResponse> responseUpdatePostById(Long id, PostRequestDTO postRequestDTO) {
        AppResponse response;
        Post post = updatePostById(id, postRequestDTO);
        if (post != null) {
            response = new SuccessResponse<ResponseDTO>("Post updated successfully.", new PostResponseDTO(post));
        } else {
            response = new ErrorResponse("Post update failed.");
        }
        return new ResponseEntity<>(response, response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Post> findAllPostByCategory(String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);
        if (category == null) {
            return null;
        }
        return postRepository.findByCategory(category);
    }

    @Override
    public ResponseEntity<AppResponse> responseFindAllPostByCategory(String categoryName) {
        AppResponse response;
        List<Post> posts = findAllPostByCategory(categoryName);
        if (posts != null) {
            List<ResponseDTO> postResponseDTOs = new ArrayList<>();
            for (Post post : posts) {
                postResponseDTOs.add(new PostResponseDTO(post));
            }
            response = new SuccessResponse<List<ResponseDTO>>("Success.", postResponseDTOs);
        } else {
            response = new ErrorResponse("Failed");
        }
        return new ResponseEntity<>(response, response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Post> findLastestPostByCategory(String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);
        if (category == null) {
            return null;
        }
        return postRepository.findLastestPostByCategory(category.getId());

    }

    @Override
    public ResponseEntity<AppResponse> responseFindLastestPostByCategory(String categoryName) {
        AppResponse response;
        List<Post> posts = findLastestPostByCategory(categoryName);
        if (posts != null) {
            List<ResponseDTO> postResponseDTOs = new ArrayList<>();
            for (Post post : posts) {
                postResponseDTOs.add(new PostResponseDTO(post));
            }
            response = new SuccessResponse<List<ResponseDTO>>("Success.", postResponseDTOs);
        } else {
            response = new ErrorResponse("Failed");
        }
        return new ResponseEntity<>(response, response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
