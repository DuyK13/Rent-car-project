package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.TagRequest;
import com.iuh.rencar_project.dto.response.TagResponse;
import com.iuh.rencar_project.entity.Tag;
import com.iuh.rencar_project.utils.mapper.annotation.StringToSlugMapping;
import com.iuh.rencar_project.utils.mapper.annotation.UserToStringMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {HelperMapper.class})
public interface ITagMapper {

    @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "slug", source = "name", qualifiedBy = StringToSlugMapping.class)})
    Tag toEntity(TagRequest tagRequest);

    @InheritConfiguration
    void updateEntity(TagRequest tagRequest, @MappingTarget Tag tag);

    @Mappings({@Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "createdBy", source = "createdBy", qualifiedBy = UserToStringMapping.class)})
    TagResponse toResponse(Tag tag);
}
