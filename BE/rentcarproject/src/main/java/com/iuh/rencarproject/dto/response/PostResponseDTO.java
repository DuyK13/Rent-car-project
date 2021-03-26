package com.iuh.rencarproject.dto.response;

import java.util.Date;

import com.iuh.rencarproject.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponseDTO implements ResponseDTO {
    Long id;
    String title;
    String shortTitle;
    Date createdDate;
    String content;
    String author;
    Long categoryId;
    String category;
    Long parentPostId;
    String parentPostTitle;

    public PostResponseDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getShortTitle(), post.getDateCreated(), post.getContent(),
                post.getUser().getName(), post.getCategory().getId(), post.getCategory().getName(),
                post.getPost() != null ? post.getPost().getId() : null,
                post.getPost() != null ? post.getPost().getTitle() : null);
    }
}
