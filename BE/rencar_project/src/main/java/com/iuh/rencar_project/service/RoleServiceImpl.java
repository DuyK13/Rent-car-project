/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iuh.rencar_project.dto.RoleDTO;
import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.repository.RoleRepository;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.utils.enums.RolesType;
import com.iuh.rencar_project.utils.exception.bind.role.RoleNotFoundException;
import com.iuh.rencar_project.utils.mapper.IRoleMapper;

import lombok.extern.slf4j.Slf4j;

@PropertySource("classpath:error.properties")
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private Environment env;

	@Autowired
	private IRoleMapper roleMapper;

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new RoleNotFoundException(env.getProperty("error.roles.notFound")));
	}

	@Transactional
	@Override
	public boolean saveAndUpdate(RoleDTO roleDTO) {
		Role role = roleMapper.toEntity(roleDTO);
		boolean isExist = roleRepository.existsByName(role.getName());
		Role currentRole = null;
		if (role.getId() != null)
			currentRole = this.findById(role.getId());
		if ((role.getId() == null && !isExist) || (role.getId() != null && (currentRole.equals(role) || !isExist))) {
			try {
				roleRepository.saveAndFlush(role);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return false;
			}
		}
		return false;
	}

	@Override
	public Role findByName(String name) {
		if (name.equals("") || name == null)
			return null;
		return roleRepository.findByName(Enum.valueOf(RolesType.class, name))
				.orElseThrow(() -> new RoleNotFoundException(env.getProperty("error.roles.notFound")));
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@PostConstruct
	private void initDefaultRole() {
		if (!roleRepository.existsByName(RolesType.ROLE_ADMIN)) {
			roleRepository.saveAndFlush(new Role(null, RolesType.ROLE_ADMIN));
		}
		if (!roleRepository.existsByName(RolesType.ROLE_MODERATOR)) {
			roleRepository.saveAndFlush(new Role(null, RolesType.ROLE_MODERATOR));
		}
	}
}
