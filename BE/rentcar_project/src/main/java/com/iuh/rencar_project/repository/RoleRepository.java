package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.utils.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Boolean existsByName(ERole name);

    Optional<Role> findByName(ERole name);
}
