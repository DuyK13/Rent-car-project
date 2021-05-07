/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EnumType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.rencar_project.dto.request.RoleRequest;
import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.repository.RoleRepository;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.utils.enums.ERole;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IRoleMapper;

@Service
public class RoleServiceImpl implements IRoleService {

	private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private IRoleMapper roleMapper;

	@Override
	public String save(RoleRequest roleRequest) {
		if (this.existsByName(roleRequest.getName()))
			throw new EntityException("Role " + roleRequest.getName() + " exists");
		try {
			roleRepository.saveAndFlush(roleMapper.toEntity(roleRequest));
		} catch (Exception e) {
			logger.error("Role Exception: ", e);
			throw new EntityException("Role " + roleRequest.getName() + " save fail", e);
		}
		return "Role " + roleRequest.getName() + " save success";
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Role with id: " + id + " not found"));
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(EnumType.valueOf(ERole.class, name))
				.orElseThrow(() -> new NotFoundException("Role " + name + " not found"));
	}

	@Override
	public Boolean existsByName(String name) {
		return roleRepository.existsByName(EnumType.valueOf(ERole.class, name));
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@PostConstruct
	private void initRole() {
		if (!this.existsByName("ROLE_ADMIN")) {
			roleRepository.saveAndFlush(new Role(1L, ERole.ROLE_ADMIN));
		}
		if (!this.existsByName("ROLE_MODERATOR")) {
			roleRepository.saveAndFlush(new Role(2L, ERole.ROLE_MODERATOR));
		}
		if (!this.existsByName("ROLE_USER")) {
			roleRepository.saveAndFlush(new Role(3L, ERole.ROLE_USER));
		}
	}
}
