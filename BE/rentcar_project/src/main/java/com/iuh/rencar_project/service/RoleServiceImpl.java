package com.iuh.rencar_project.service;

import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.repository.RoleRepository;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.utils.enums.ERole;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EnumType;
import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:09 AM
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private Boolean existsByName(String name) {
        return roleRepository.existsByName(EnumType.valueOf(ERole.class, name));
    }

    private void save(Role role) {
        try {
            roleRepository.saveAndFlush(role);
        } catch (Exception e) {
            logger.error("Role Exception: ", e);
        }
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Role not found"));
    }

    @PostConstruct
    private void initRole() {
        if (!this.existsByName(ERole.ROLE_ADMIN.name())) {
            this.save(new Role(0L, ERole.ROLE_ADMIN));
        }
        if (!this.existsByName(ERole.ROLE_MODERATOR.name())) {
            this.save(new Role(0L, ERole.ROLE_MODERATOR));
        }
        if (!this.existsByName(ERole.ROLE_STAFF.name())) {
            this.save(new Role(0L, ERole.ROLE_STAFF));
        }
    }
}
