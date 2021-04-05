///**
// * @author DuyTT10
// * @date Mar 23, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.service;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.iuh.rencar_project.dto.request.RoleRequestDTO;
//import com.iuh.rencar_project.entity.Role;
//import com.iuh.rencar_project.repository.RoleRepository;
//import com.iuh.rencar_project.service.template.IRoleService;
//
//@Service
//public class RoleServiceImpl implements IRoleService {
//
//	final static Logger logger = Logger.getLogger(UserServiceImpl.class);
//
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Override
//	public Role findByName(String roleName) {
//		return roleRepository.findByName(roleName);
//	}
//
//	@Override
//	public List<Role> findAll() {
//		List<Role> role = null;
//		try {
//			role = roleRepository.findAll();
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return role;
//	}
//
//	@Override
//	public Role addRole(RoleRequestDTO roleCreateDTO) {
//		Role role = roleCreateDTO.convertRoleCreateToEntity();
//		try {
//			role = roleRepository.saveAndFlush(role);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return null;
//		}
//		return role;
//	}
//
//}
