package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.CourseRequest;
import com.iuh.rencar_project.dto.response.CourseResponse;
import com.iuh.rencar_project.entity.Course;
import com.iuh.rencar_project.utils.mapper.annotation.StringToSlugMapping;
import com.iuh.rencar_project.utils.mapper.annotation.UserToStringMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.*;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:09 AM
 */
@Mapper(componentModel = "spring", uses = {HelperMapper.class})
public interface ICourseMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "slug", source = "title", qualifiedBy = StringToSlugMapping.class),
            @Mapping(target = "timeCourse", source = "timeCourse"),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    Course toEntity(CourseRequest courseRequest);

    @InheritConfiguration(name = "toEntity")
    void updateEntity(CourseRequest courseRequest, @MappingTarget Course course);

    @Mappings({
            @Mapping(target = "createdBy", source = "createdBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedBy = UserToStringMapping.class)
    })
    CourseResponse toResponse(Course course);
}
