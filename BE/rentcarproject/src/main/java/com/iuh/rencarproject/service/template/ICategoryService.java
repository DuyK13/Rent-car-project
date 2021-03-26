package com.iuh.rencarproject.service.template;

import com.iuh.rencarproject.entity.Category;

public interface ICategoryService {
	
    Category findCategoryByName(String name);
}
