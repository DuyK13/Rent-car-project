package com.iuh.rencarproject.dto.request;

import lombok.Data;

@Data
public class PostRequestDTO {
    String title;
    String shortTitle;
    String content;
    String userName;
    String category;
    Long postId;
}
