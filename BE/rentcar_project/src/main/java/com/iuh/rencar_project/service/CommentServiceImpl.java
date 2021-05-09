package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CommentRequest;
import com.iuh.rencar_project.entity.Comment;
import com.iuh.rencar_project.repository.CommentRepository;
import com.iuh.rencar_project.service.template.ICommentService;
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

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ICommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public String save(CommentRequest commentRequest) {
        try {
            commentRepository.saveAndFlush(commentMapper.toEntity(commentRequest));
        } catch (Exception e) {
            logger.error("Comment Exception: ", e);
            throw new EntityException("Comment save fail");
        }
        return "Comment save success";
    }

    @Override
    public String update(Long id) {
        Comment currentComment = this.findById(id);
        String message;
        try {
            currentComment.setStatus(Status.ACTIVE);
            message = "Comment " + currentComment.getId() + " active success";
        } catch (Exception e) {
            logger.error("Comment Exception: ", e);
            throw new EntityException("Comment " + currentComment.getId() + " change status fail");
        }
        return message;
    }

    @Override
    public String delete(Long id) {
        Comment comment = this.findById(id);
        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Comment Exception: ", e);
            throw new EntityException("Commnet " + id + " delete fail");
        }
        return "Comment " + id + "delete success";
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment with id: " + id + " not found"));
    }

    @Override
    public Page<Comment> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return commentRepository.findAll(pageable);
    }

}
