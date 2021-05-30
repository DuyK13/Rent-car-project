package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CommentRequest;
import com.iuh.rencar_project.entity.Comment;
import com.iuh.rencar_project.repository.CommentRepository;
import com.iuh.rencar_project.service.template.ICommentService;
import com.iuh.rencar_project.service.template.IPostService;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.ICommentMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 2:07 PM
 */
@Service
public class CommentServiceImpl implements ICommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;
    private final ICommentMapper commentMapper;
    private final IPostService postService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ICommentMapper commentMapper, IPostService postService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postService = postService;
    }

    @Override
    public String save(String postSlug, CommentRequest commentRequest) {
        Comment comment = commentMapper.toEntity(commentRequest);
        try {
            commentRepository.saveAndFlush(comment);
        } catch (Exception e) {
            logger.error("Comment Exception: ", e);
            throw new EntityException("Comment save failed");
        }
        return "Comment save successful and waiting for approved";
    }

    @Override
    public String setAvailability(Long id) {
        Comment currentComment = this.findById(id);
        String message;
        currentComment.setStatus(Status.ENABLE);
        try {
            commentRepository.saveAndFlush(currentComment);
        } catch (Exception e) {
            logger.error("Comment Exception: ", e);
            throw new EntityException("Comment status change failed");
        }
        return "Enable comment successful";
    }

    @Override
    public String delete(Long id) {
        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Comment Exception: ", e);
            throw new EntityException("Comment delete failed");
        }
        return "Comment delete successful";
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
    }

    @Override
    public Page<Comment> findAllPaginatedDisable(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return commentRepository.findAllByStatus(pageable, Status.DISABLE);
    }

    @Override
    public Page<Comment> findAllPaginatedEnable(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return commentRepository.findAllByStatus(pageable, Status.ENABLE);
    }

    @Override
    public Page<Comment> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return commentRepository.findAll(pageable);
    }
}
