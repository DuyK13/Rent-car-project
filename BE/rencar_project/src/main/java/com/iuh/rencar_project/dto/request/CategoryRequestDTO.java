//package com.iuh.rencar_project.dto.request;
//
//import java.util.Date;
//
//import com.iuh.rencar_project.entity.Category;
//import com.iuh.rencar_project.entity.User;
//import com.iuh.rencar_project.utils.DeleteStatus;
//
//import lombok.Data;
//
//@Data
//public class CategoryRequestDTO {
//	
//    private String name;
//    
//    private Date categoryDate;
//    
//    private String content;
//    
//    private Long view;
//    
//    private int status;
//    
//    private Long userId;
//    
//    public Category convertCategoryCreateToEntity(User user) {
//    	Category category = new Category();
//    	category.setCategoryDate(categoryDate);
//    	category.setContent(content);
//    	category.setView(0L);
//    	category.setStatus(DeleteStatus.ACTIVE.ordinal());
//    	category.setUser(user);
//    	return category;
//    }
//}
