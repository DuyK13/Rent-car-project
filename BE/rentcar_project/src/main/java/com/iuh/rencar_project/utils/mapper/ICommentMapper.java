package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.CommentRequest;
import com.iuh.rencar_project.dto.response.CommentResponse;
import com.iuh.rencar_project.entity.Comment;
import com.iuh.rencar_project.utils.mapper.annotation.CommentLevelMapping;
import com.iuh.rencar_project.utils.mapper.annotation.LongToCommentMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToPostMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 2:03 PM
 */
@Mapper(componentModel = "spring", uses = {HelperMapper.class})
public interface ICommentMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "dateCreated", ignore = true),
            @Mapping(target = "likes", ignore = true),
            @Mapping(target = "dislike", ignore = true),
            @Mapping(target = "level", source = "parentId", qualifiedBy = CommentLevelMapping.class),
            @Mapping(target = "status", expression = "java(com.iuh.rencar_project.utils.enums.Status.DISABLE)"),
            @Mapping(target = "parent", source = "parentId", qualifiedBy = LongToCommentMapping.class),
            @Mapping(target = "post", source = "postTitle", qualifiedBy = StringToPostMapping.class)
    })
    Comment toEntity(CommentRequest commentRequest);

    CommentResponse toResponse(Comment comment);
}
