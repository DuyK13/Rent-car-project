package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Course;
import com.iuh.rencar_project.utils.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findBySlug(String slug);

    Optional<Course> findByTitle(String title);

    Boolean existsByTitle(String title);

    List<Course> findAllByStatus(Status status);

    Page<Course> findAllByStatus(Status status, Pageable pageable);
}
