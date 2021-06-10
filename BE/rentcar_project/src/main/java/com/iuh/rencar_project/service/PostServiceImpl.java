package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.PostRequest;
import com.iuh.rencar_project.entity.Post;
import com.iuh.rencar_project.entity.Tag;
import com.iuh.rencar_project.repository.PostRepository;
import com.iuh.rencar_project.service.template.IFileService;
import com.iuh.rencar_project.service.template.IPostService;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IPostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 12:02 PM
 */
@Service
public class PostServiceImpl implements IPostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;

    private final IPostMapper postMapper;

    private final IFileService fileService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, IPostMapper postMapper, IFileService fileService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.fileService = fileService;
    }

    @Override
    public String save(PostRequest postRequest, MultipartFile multipartFile) {
        String title = postRequest.getTitle();
        if (this.existsByTitle(title))
            throw new EntityException("Post exists");
        String fileUrl = fileService.uploadPostImage(multipartFile, title);
        Post post = postMapper.toEntity(postRequest);
        post.setImage(fileUrl);
        try {
            postRepository.saveAndFlush(post);
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post save failed", e);
        }
        return "Post save successful";
    }

    @Override
    public String update(Long id, PostRequest postRequest, MultipartFile multipartFile) {
        Post currentPost = this.findById(id);
            if (this.existsByTitle(postRequest.getTitle()) && !currentPost.getTitle().equals(postRequest.getTitle()))
            throw new EntityException("Post exists");
        postMapper.updateEntity(postRequest, currentPost);
        currentPost.setImage(fileService.updatePostImage(multipartFile, currentPost));
        try {
            postRepository.saveAndFlush(currentPost);
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post update failed", e);
        }
        return "Post update successful";
    }

    @Override
    public String update(Long id, PostRequest postRequest) {
        Post currentPost = this.findById(id);
        if (this.existsByTitle(postRequest.getTitle()) && !currentPost.getTitle().equals(postRequest.getTitle()))
            throw new EntityException("Post exists");
        postMapper.updateEntity(postRequest, currentPost);
        try {
            postRepository.saveAndFlush(currentPost);
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post update failed", e);
        }
        return "Post update successful";
    }

    @Override
    public String setAvailability(Long id) {
        Post currentPost = this.findById(id);
        String message;
        if (currentPost.getStatus() == Status.ENABLE) {
            currentPost.setStatus(Status.DISABLE);
            message = "Disable post successful";
        } else {
            currentPost.setStatus(Status.ENABLE);
            message = "Enable post successful";
        }
        try {
            postRepository.saveAndFlush(currentPost);
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post status change failed");
        }
        return message;
    }

    @Override
    public String delete(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Post Exception: ", e);
            throw new EntityException("Post delete failed");
        }
        return "Post delete successful";
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public Post findBySlug(String slug) {
        return postRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public Post findBySlugForGuest(String slug) {
        return postRepository.findBySlugAndStatusIs(slug, Status.ENABLE).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public Page<Post> findAllPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findAllPaginatedForGuest(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return postRepository.findAllByStatusIs(Status.ENABLE, pageable);
    }

    @Override
    public Page<Post> findAllPaginatedByTagForGuest(Tag tag, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return postRepository.findByTagsIsContainingAndStatusIs(tag, Status.ENABLE, pageable);
    }

    @Override
    public Boolean existsByTitle(String title) {
        return postRepository.existsByTitle(title);
    }

    @Override
    public List<Post> findByTag(Tag tag) {
        return postRepository.findByTagsIsContaining(tag);
    }

    @Override
    public Post findByTitle(String title) {
        return postRepository.findByTitle(title).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public Page<Post> search(int pageNo, int pageSize, String s) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return postRepository.search(s, pageable);
    }
}
