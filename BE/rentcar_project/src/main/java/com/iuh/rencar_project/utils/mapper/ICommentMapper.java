package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.CommentRequest;
import com.iuh.rencar_project.dto.response.CommentResponse;
import com.iuh.rencar_project.entity.Comment;
import com.iuh.rencar_project.utils.mapper.annotation.LongToCommentMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.*;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 2:03 PM
 */
@Mapper(componentModel = "spring", uses = {HelperMapper.class})
public interface ICommentMapper {

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "likes", ignore = true),
            @Mapping(target = "dislike", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "comment", source = "commentId", qualifiedBy = LongToCommentMapping.class)
    })
    Comment toEntity(CommentRequest commentRequest);

    @InheritConfiguration
    void updateEntity(CommentRequest commentRequest, @MappingTarget Comment comment);

    CommentResponse toResponse(Comment comment);
}
