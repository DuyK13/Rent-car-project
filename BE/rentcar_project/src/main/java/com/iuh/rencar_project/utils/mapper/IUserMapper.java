/*
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.dto.response.UserResponse;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.utils.mapper.annotation.PasswordEncodedMapping;
import com.iuh.rencar_project.utils.mapper.annotation.RoleToStringMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToRoleMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {HelperMapper.class})
public interface IUserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password", qualifiedBy = PasswordEncodedMapping.class),
            @Mapping(target = "email", source = "email", defaultValue = ""),
            @Mapping(target = "status", expression = "java(com.iuh.rencar_project.utils.enums.Status.ENABLE)"),
            @Mapping(target = "roles", source = "roles", qualifiedBy = StringToRoleMapping.class)
    })
    User createUser(UserRequest userRequest);

    @Mapping(target = "roles", source = "roles", qualifiedBy = RoleToStringMapping.class)
    UserResponse toResponse(User user);

}
