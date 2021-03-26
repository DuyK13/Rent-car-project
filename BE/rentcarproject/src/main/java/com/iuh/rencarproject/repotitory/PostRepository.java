package com.iuh.rencarproject.repotitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iuh.rencarproject.entity.Category;
import com.iuh.rencarproject.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategory(Category category);

    @Query(value = "SELECT * FROM Post p WHERE p.Category_id = ?1 ORDER BY p.date_created DESC LIMIT 5", nativeQuery = true)
    List<Post> findLastestPostByCategory(Long categoryId);
}
