package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Comment;
import com.iuh.rencar_project.utils.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByStatus(Pageable pageable, Status disable);
}
