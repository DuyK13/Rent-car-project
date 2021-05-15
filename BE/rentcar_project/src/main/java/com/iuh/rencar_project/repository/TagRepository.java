package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	Boolean existsByName(String name);

	Optional<Tag> findBySlug(String slug);

	Optional<Tag> findByName(String name);
}
