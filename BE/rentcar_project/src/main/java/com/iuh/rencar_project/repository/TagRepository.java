package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Boolean existsByName(String name);

    Optional<Tag> findBySlug(String slug);

    Optional<Tag> findByName(String name);

    @Query(value = "SELECT t FROM Tag t WHERE CONCAT(t.name, ' ', t.createdBy.username, ' ', t.modifiedBy.username, ' ', t.createdDate, ' ', t.modifiedDate, ' ') LIKE %?1%")
    Page<Tag> search(String s, Pageable pageable);
}
