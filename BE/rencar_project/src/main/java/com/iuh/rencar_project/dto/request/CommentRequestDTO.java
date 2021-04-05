package com.iuh.rencar_project.dto.request;

import lombok.Data;

@Data
public class CommentRequestDTO {
   private String name;
   private String email;
   private String content;
   private Long postId;
   private Long courseId;
   private Long parentCommendId;
}
