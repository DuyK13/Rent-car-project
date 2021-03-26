package com.iuh.rencarproject.repotitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.rencarproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String userName);

	User findByUsernameAndPassword(String username, String password);

	Page<User> findAll(Pageable pageRequest);

	boolean existsByUsername(String username);

	boolean existsByPhoneNumberAndPhoneNumberNotNull(String phoneNumber);

	boolean existsByEmailAndEmailNotNull(String email);
}