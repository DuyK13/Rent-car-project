package com.iuh.rencar_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.rencar_project.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	Boolean existsByName(String name);

	Optional<Tag> findBySlug(String slug);

}