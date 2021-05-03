package com.iuh.rencar_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iuh.rencar_project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUsername(String username);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	Optional<User> findByUsername(String username);
}
