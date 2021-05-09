package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CategoryRequest;
import com.iuh.rencar_project.entity.Category;
import com.iuh.rencar_project.repository.CategoryReposity;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.mapper.ICategoryMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 1:14 PM
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);
    private final CategoryReposity categoryReposity;
    private final ICategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryReposity categoryReposity, ICategoryMapper categoryMapper) {
        this.categoryReposity = categoryReposity;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public String save(CategoryRequest categoryRequest) {
        String name = categoryRequest.getName();
        if (this.existsByName(name))
            throw new EntityException("Category " + name + " exists");
        try {
            categoryReposity.saveAndFlush(categoryMapper.toEntity(categoryRequest));
        } catch (Exception e) {
            logger.error("Category Exception: ", e);
            throw new EntityException("Category " + name + " save fail", e);
        }
        return "Category " + name + " save success";
    }

    @Override
    public String update(Long id, CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public String update(Long id) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public Category findByBySlug(String slug) {
        return null;
    }

    @Override
    public Page<Category> findAllPaginated(int pageNo) {
        return null;
    }

    @Override
    public Boolean existsByName(String name) {
        return null;
    }

    @Override
    public Category findByName(String name) {
        return null;
    }
}
