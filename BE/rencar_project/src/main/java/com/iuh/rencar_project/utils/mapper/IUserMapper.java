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

import com.iuh.rencar_project.dto.request.PasswordRequestDTO;
import com.iuh.rencar_project.dto.request.UserRequestDTO;
import com.iuh.rencar_project.dto.response.UserResponseDTO;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.utils.mapper.annotation.EncodedMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StatusDecoderMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StatusEncoderMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToRoleMapping;
import com.iuh.rencar_project.utils.mapper.helper.CollectionMapper;
import com.iuh.rencar_project.utils.mapper.helper.EnumMapper;
import com.iuh.rencar_project.utils.mapper.helper.PasswordEncoderMapper;

@Mapper(componentModel = "spring", uses = { IRoleMapper.class, PasswordEncoderMapper.class, EnumMapper.class,
		CollectionMapper.class })
public interface IUserMapper {

//	@Mapping(source = "userDTO.password", target = "password", qualifiedBy = EncodedMapping.class)
//	@Mapping(source = "userDTO.status", target = "status", qualifiedBy = StatusEncoderMapping.class)
//	User toEntity(UserResponseDTO userDTO);

	@Mapping(source = "userRequestDTO.roles", target = "roles", qualifiedBy = StringToRoleMapping.class)
	@Mapping(source = "userRequestDTO.status", target = "status", qualifiedBy = StatusEncoderMapping.class)
	@Mapping(source = "userRequestDTO.password", target = "password", qualifiedBy = EncodedMapping.class)
	User toEntity(UserRequestDTO userRequestDTO);

	@Mapping(source = "user.status", target = "status", qualifiedBy = StatusDecoderMapping.class)
	UserResponseDTO toDTO(User user);

	List<UserResponseDTO> toListDTO(List<User> users);

	@Mappings({ @Mapping(target = "password", ignore = true), @Mapping(target = "roles", ignore = true),
			@Mapping(target = "username", source = "userRequestDTO.username"), @Mapping(target = "id", ignore = true),
			@Mapping(target = "status", ignore = true) })
	void updateEntity(UserRequestDTO userRequestDTO, @MappingTarget User user);

	@Mappings({
			@Mapping(source = "passwordRequestDTO.newPassword", target = "password", qualifiedBy = EncodedMapping.class),
			@Mapping(target = "roles", ignore = true), @Mapping(target = "username", ignore = true),
			@Mapping(target = "id", ignore = true), @Mapping(target = "status", ignore = true) })
	void updateEntity(PasswordRequestDTO passwordRequestDTO, @MappingTarget User user);

}
