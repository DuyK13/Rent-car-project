package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.CarRequest;
import com.iuh.rencar_project.dto.response.CarResponse;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.utils.mapper.annotation.StringToSlugMapping;
import com.iuh.rencar_project.utils.mapper.annotation.UserToStringMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.*;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 1:50 PM
 */
@Mapper(componentModel = "spring", uses = {HelperMapper.class})
public interface ICarMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "manufacturingYear", source = "manufacturingYear"),
            @Mapping(target = "costPerHour", source = "costPerHour"),
            @Mapping(target = "slug", source = "name", qualifiedBy = StringToSlugMapping.class),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "status", expression = "java(com.iuh.rencar_project.utils.enums.Status.ENABLE)")
    })
    Car toEntity(CarRequest carRequest);

    @InheritConfiguration
    void updateEntity(CarRequest carRequest, @MappingTarget Car car);

    @Mappings({
            @Mapping(target = "createdBy", source = "createdBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedBy = UserToStringMapping.class)
    })
    CarResponse toResponse(Car car);
}
