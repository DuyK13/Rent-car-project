/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.iuh.rencar_project.dto.RoleDTO;
import com.iuh.rencar_project.entity.Role;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

	@Mappings({ @Mapping(source = "roleDTO.name", target = "name"), @Mapping(target = "id", ignore = true) })
	Role toEntity(RoleDTO roleDTO);

	RoleDTO toDTO(Role role);
	
	List<RoleDTO> toListDTO(List<Role> roles);

	void updateEntity(RoleDTO roleDTO, @MappingTarget Role role);
}
