package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Category;
import com.iuh.rencar_project.utils.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryReposity extends JpaRepository<Category, Long> {

    Optional<Category> findBySlug(String slug);

    Optional<Category> findByName(String name);

    Optional<Category> findByCarsIsContaining(Car car);

    Boolean existsByName(String name);

    Page<Category> findAllByStatusIs(Pageable pageable, Status status);

    Optional<Category> findBySlugAndStatusIs(String slug, Status active);

    List<Category> findALlByStatusAndParentIsNotNull(Status status);
}
