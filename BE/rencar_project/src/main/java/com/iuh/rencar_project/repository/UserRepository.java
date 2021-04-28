package com.iuh.rencar_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.rencar_project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);
	
	boolean existsByIdAndPassword(Long id, String password);
}
