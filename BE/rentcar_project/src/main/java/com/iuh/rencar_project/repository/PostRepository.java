package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Post;
import com.iuh.rencar_project.entity.Tag;
import com.iuh.rencar_project.utils.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Boolean existsByTitle(String title);

    Optional<Post> findBySlug(String slug);

    Page<Post> findByTagsIsContainingAndStatusIs(Tag tag, Status status, Pageable pageable);

    List<Post> findByTagsIsContaining(Tag tag);

    Page<Post> findAllByStatusIs(Status status, Pageable pageable);

    Optional<Post> findBySlugAndStatusIs(String slug, Status active);

    Optional<Post> findByTitle(String title);

    @Query(value = "SELECT p FROM Post p WHERE CONCAT(p.title, ' ', p.modifiedDate, ' ', p.createdDate, ' ', p.createdBy.username, ' ', p.modifiedBy.username, ' ') LIKE %?1%")
    Page<Post> search(String s, Pageable pageable);
}
