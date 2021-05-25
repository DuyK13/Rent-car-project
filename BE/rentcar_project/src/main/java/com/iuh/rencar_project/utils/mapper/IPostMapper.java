package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.PostRequest;
import com.iuh.rencar_project.dto.response.PostResponse;
import com.iuh.rencar_project.entity.Post;
import com.iuh.rencar_project.utils.mapper.annotation.StringToSlugMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToTagMapping;
import com.iuh.rencar_project.utils.mapper.annotation.TagToStringMapping;
import com.iuh.rencar_project.utils.mapper.annotation.UserToStringMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.*;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 12:06 PM
 */
@Mapper(componentModel = "spring", uses = {HelperMapper.class})
public interface IPostMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "slug", source = "title", qualifiedBy = StringToSlugMapping.class),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "tags", source = "tags", qualifiedBy = StringToTagMapping.class)
    })
    Post toEntity(PostRequest postRequest);

    @InheritConfiguration
    void updateEntity(PostRequest postRequest, @MappingTarget Post post);

    @Mappings({
            @Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "createdBy", source = "createdBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "tags", source = "tags", qualifiedBy = TagToStringMapping.class)
    })
    PostResponse toResponse(Post post);

}
