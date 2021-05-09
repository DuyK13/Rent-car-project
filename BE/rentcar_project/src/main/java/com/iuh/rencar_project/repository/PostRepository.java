package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Boolean existsByTitle(String title);

    Optional<Post> findBySlug(String slug);
}
