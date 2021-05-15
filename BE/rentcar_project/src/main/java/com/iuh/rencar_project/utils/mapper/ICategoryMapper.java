package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.CategoryRequest;
import com.iuh.rencar_project.dto.response.CategoryResponse;
import com.iuh.rencar_project.entity.Category;
import com.iuh.rencar_project.utils.mapper.annotation.StringToCategoryMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToSlugMapping;
import com.iuh.rencar_project.utils.mapper.annotation.UserToStringMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.*;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 1:17 PM
 */
@Mapper(componentModel = "spring", uses = {HelperMapper.class, ICarMapper.class})
public interface ICategoryMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "slug", source = "name", qualifiedBy = StringToSlugMapping.class),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "category", source = "parentCategory", qualifiedBy = StringToCategoryMapping.class),
            @Mapping(target = "cars", ignore = true)
    })
    Category toEntity(CategoryRequest categoryRequest);

    @InheritConfiguration
    void updateEntity(CategoryRequest categoryRequest, @MappingTarget Category category);

    @Mappings({
            @Mapping(target = "createdBy", source = "createdBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedBy = UserToStringMapping.class)
    })
    CategoryResponse toResponse(Category category);
}
