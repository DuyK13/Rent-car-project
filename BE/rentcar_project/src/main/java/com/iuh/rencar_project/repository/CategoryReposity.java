package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReposity extends JpaRepository<Category, Long> {

}
