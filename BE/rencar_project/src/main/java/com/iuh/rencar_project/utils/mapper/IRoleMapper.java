/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.iuh.rencar_project.dto.request.RoleRequest;
import com.iuh.rencar_project.dto.response.RoleResponse;
import com.iuh.rencar_project.entity.Role;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

	@Mapping(target = "id", ignore = true)
	Role toEntity(RoleRequest roleRequest);

	@InheritConfiguration(name = "toEntity")
	void updateEntity(RoleRequest roleRequest, @MappingTarget Role role);

	RoleResponse toResponse(Role role);
}
