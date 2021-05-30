package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.CommentRequest;
import com.iuh.rencar_project.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.stream.DoubleStream;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 2:07 PM
 */
public interface ICommentService {
    String save(String postSlug, CommentRequest commentRequest);

    String setAvailability(Long id);

    String delete(Long id);

    Comment findById(Long id);

    Page<Comment> findAllPaginatedDisable(int pageNo);

    Page<Comment> findAllPaginated(int pageNo);

    Page<Comment> findAllPaginatedEnable(int pageNo);
}
