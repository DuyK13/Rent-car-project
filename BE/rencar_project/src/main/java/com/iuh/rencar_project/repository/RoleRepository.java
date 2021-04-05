package com.iuh.rencar_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.rencar_project.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
