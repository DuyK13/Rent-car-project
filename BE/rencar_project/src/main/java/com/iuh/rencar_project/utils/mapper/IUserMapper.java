/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.dto.response.UserResponse;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.utils.mapper.annotation.PasswordEncodedMapping;
import com.iuh.rencar_project.utils.mapper.annotation.RoleToStringMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToRoleMapping;
import com.iuh.rencar_project.utils.mapper.helper.CollectionMapper;
import com.iuh.rencar_project.utils.mapper.helper.PasswordEncoderMapper;

@Mapper(componentModel = "spring", uses = { PasswordEncoderMapper.class, CollectionMapper.class })
public interface IUserMapper {
	@Mappings({ @Mapping(target = "roles", source = "roles", qualifiedBy = StringToRoleMapping.class),
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "password", source = "password", qualifiedBy = PasswordEncodedMapping.class) })
	User toEntity(UserRequest userRequest);

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "password", ignore = true),
		@Mapping(target = "status", ignore = true),
		@Mapping(target = "roles", ignore = true)
	})
	void updateUserInformation(UserRequest userRequest, @MappingTarget User user);

	@Mapping(target = "roles", source = "roles", qualifiedBy = RoleToStringMapping.class)
	UserResponse toResponse(User user);

}
