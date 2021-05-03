package com.iuh.rencar_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.rencar_project.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
