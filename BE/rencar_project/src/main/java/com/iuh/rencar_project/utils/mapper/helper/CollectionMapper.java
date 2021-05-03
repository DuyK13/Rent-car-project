/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.utils.mapper.annotation.RoleToStringMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToRoleMapping;

@Component
public class CollectionMapper {

	@Autowired
	private IRoleService roleService;

	@StringToRoleMapping
	public Role toRole(String value) {
		return roleService.findByName(value);
	}

	@RoleToStringMapping
	public String toString(Role role) {
		return role.getName().name();
	}
}
