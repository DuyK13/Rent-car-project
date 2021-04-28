/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.utils.mapper.annotation.StringToRoleMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringsToRolesMapping;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionMapper {

	@Autowired
	IRoleService roleService;

	@StringToRoleMapping
	public Role toRole(String value) {
		return roleService.findByName(value);
	}

	@StringsToRolesMapping
	public List<Role> toRoles(List<String> values) {
		return values.stream().map(x -> {
			return toRole(x);
		}).collect(Collectors.toList());
	}
}
