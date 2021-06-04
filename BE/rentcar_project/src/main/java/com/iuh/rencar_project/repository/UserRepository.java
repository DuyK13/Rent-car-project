package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE CONCAT(u.username, ' ', u.email, ' ', u.roles, ' ') LIKE %?1%")
    Page<User> search(String s, Pageable pageable);
}
