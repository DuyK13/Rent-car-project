/**
 * @author DuyTT10
 * @date Mar 23, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.rencarproject.dto.request.RoleCreateDTO;
import com.iuh.rencarproject.entity.Role;
import com.iuh.rencarproject.repotitory.RoleRepository;
import com.iuh.rencarproject.service.template.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByName(String roleName) {
		return roleRepository.findByName(roleName);
	}

	@Override
	public List<Role> findAll() {
		List<Role> role = null;
		try {
			role = roleRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return role;
	}

	@Override
	public Role addRole(RoleCreateDTO roleCreateDTO) {
		Role role = roleCreateDTO.convertToEntity();
		try {
			role = roleRepository.saveAndFlush(role);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return role;
	}

}
