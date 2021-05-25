package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.PostRequest;
import com.iuh.rencar_project.entity.Comment;
import com.iuh.rencar_project.entity.Post;
import com.iuh.rencar_project.entity.Tag;
import com.iuh.rencar_project.repository.PostRepository;
import com.iuh.rencar_project.service.template.IPostService;
import com.iuh.rencar_project.service.template.ITagService;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IPostMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 12:02 PM
 */
@Service
public class PostServiceImpl implements IPostService {

    private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;

    private final IPostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, IPostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public String save(PostRequest postRequest) {
        String title = postRequest.getTitle();
        if (this.existsByTitle(title))
            throw new EntityException("Post " + title + " exists");
        try {
            postRepository.saveAndFlush(postMapper.toEntity(postRequest));
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post " + title + " save fail", e);
        }
        return "Post " + title + " save success ";
    }

    @Override
    public String update(Long id, PostRequest postRequest) {
        Post currentPost = this.findById(id);
        String currentTitle = currentPost.getTitle();
        if (this.existsByTitle(postRequest.getTitle()) && !currentTitle.equals(postRequest.getTitle()))
            throw new EntityException("Post " + postRequest.getTitle() + " exists");
        try {
            postMapper.updateEntity(postRequest, currentPost);
            postRepository.saveAndFlush(currentPost);
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post " + currentTitle + " update fail");
        }
        return "Post " + currentTitle + " update success";
    }

//    @Override
//    public void updatePostComment(String slug, Comment comment) {
//        Post post = this.findBySlug(slug);
//        try{
//            Set<Comment> comments = post.getComments();
//            if(!comments.add(comment))
//                throw new EntityException("Comment fail");
//            post.setComments(comments);
//            postRepository.saveAndFlush(post);
//        }catch (Exception e){
//            logger.error("Post Exception: ", e);
//        }
//    }

    @Override
    public String update(Long id) {
        Post currentPost = this.findById(id);
        String title = currentPost.getTitle();
        String message;
        try {
            Status status = currentPost.getStatus();
            if (status == Status.ACTIVE) {
                currentPost.setStatus(Status.INACTIVE);
                postRepository.saveAndFlush(currentPost);
                message = "Post " + title + " inactive success";
            } else {
                currentPost.setStatus(Status.ACTIVE);
                postRepository.saveAndFlush(currentPost);
                message = "Post " + title + " active success";
            }
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post " + title + " change status fail");
        }
        return message;
    }

    @Override
    public String delete(Long id) {
        Post post = this.findById(id);
        String title = post.getTitle();
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post " + title + " delete fail");
        }
        return "Post " + title + " delete success";
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post with id " + id + " not found"));
    }

    @Override
    public Post findBySlug(String slug) {
        return postRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Post with slug: " + slug + " not found"));
    }

    @Override
    public Post findBySlugForGuest(String slug) {
        return postRepository.findBySlugAndStatusIs(slug, Status.ACTIVE).orElseThrow(() -> new NotFoundException("Post with slug: " + slug + " not found"));
    }

    @Override
    public Page<Post> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 5, Sort.by(Sort.Order.asc("id")));
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findAllPaginatedForGuest(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 5, Sort.by(Sort.Order.asc("id")));
        return postRepository.findAllByStatusIs(Status.ACTIVE, pageable);
    }

    @Override
    public Page<Post> findAllPaginatedByTagForGuest(Tag tag, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo -1 , 5, Sort.by(Sort.Order.asc("id")));
        return postRepository.findByTagsIsContainingAndStatusIs(tag, Status.ACTIVE, pageable);
    }

    @Override
    public Boolean existsByTitle(String title) {
        return postRepository.existsByTitle(title);
    }

    @Override
    public List<Post> findPostsByTag(Tag tag) {
        return postRepository.findByTagsIsContaining(tag);
    }
}
