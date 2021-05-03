/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.utils.StringUtils;
import com.iuh.rencar_project.utils.mapper.annotation.RoleToStringMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToRoleMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToSlugMapping;
import com.iuh.rencar_project.utils.mapper.annotation.UserToStringMapping;

@Component
public class HelperMapper {

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

	@StringToSlugMapping
	public String toString(String value) {
		return StringUtils.unAccent(value);
	}

	@UserToStringMapping
	public String toString(User user) {
		if (user == null)
			return null;
		return user.getUsername();
	}
}
