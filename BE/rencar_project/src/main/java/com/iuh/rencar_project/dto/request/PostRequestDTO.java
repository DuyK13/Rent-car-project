package com.iuh.rencar_project.dto.request;

import lombok.Data;

@Data
public class PostRequestDTO {
	
    String title;
    
    String shortTitle;
    
    String content;
    
    String userName;
    
    int deleteStatus;
    
    String categoryName;
    
    Long postId;
    
}
