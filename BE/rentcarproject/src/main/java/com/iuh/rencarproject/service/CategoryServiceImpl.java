package com.iuh.rencarproject.service;

import com.iuh.rencarproject.entity.Category;
import com.iuh.rencarproject.repotitory.CategoryRepository;
import com.iuh.rencarproject.service.template.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByName(String name) {
        Category category = null;
        try {
            category = categoryRepository.findByName(name);
        } catch (Exception e) {
            return category;
        }
        return category;
    }

}
