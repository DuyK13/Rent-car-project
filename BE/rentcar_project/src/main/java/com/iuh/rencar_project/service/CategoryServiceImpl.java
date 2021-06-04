package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CategoryRequest;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Category;
import com.iuh.rencar_project.repository.CategoryReposity;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.ICategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 1:14 PM
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryReposity categoryReposity;
    private final ICategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryReposity categoryReposity, ICategoryMapper categoryMapper) {
        this.categoryReposity = categoryReposity;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public String save(CategoryRequest categoryRequest) {
        String name = categoryRequest.getName();
        if (this.existsByName(name))
            throw new EntityException("Category exists");
        try {
            categoryReposity.saveAndFlush(categoryMapper.toEntity(categoryRequest));
        } catch (Exception e) {
            logger.error("Category Exception: ", e);
            throw new EntityException("Category save failed", e);
        }
        return "Category save successful";
    }

    @Override
    public String update(Long id, CategoryRequest categoryRequest) {
        Category currentCategory = this.findById(id);
        if (this.existsByName(categoryRequest.getName()) && !currentCategory.getName().equals(categoryRequest.getName()))
            throw new EntityException("Category exists");
        categoryMapper.updateEntity(categoryRequest, currentCategory);
        try {
            categoryReposity.saveAndFlush(currentCategory);
        } catch (Exception e) {
            logger.error("Category Exception: ", e);
            throw new EntityException("Category update fail");
        }
        return "Category update successful";
    }

    @Override
    public String setAvailability(Long id) {
        Category currentCategory = this.findById(id);
        String message;
        if (currentCategory.getStatus() == Status.ENABLE) {
            currentCategory.setStatus(Status.DISABLE);
            message = "Disable category success";
        } else {
            currentCategory.setStatus(Status.ENABLE);
            message = "Enable category success";
        }
        try {
            categoryReposity.saveAndFlush(currentCategory);
        } catch (Exception e) {
            logger.error("Category Exception: ", e);
            throw new EntityException("Category status change failed");
        }
        return message;
    }

    @Override
    public String addCarToCategory(String name, Car car) {
        Category category = this.findByName(name);
        Set<Car> cars = category.getCars();
        if (!cars.add(car))
            throw new EntityException("Car save failed");
        category.setCars(cars);
        try {
            categoryReposity.save(category);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
            throw new EntityException("Car save failed");
        }
        return "Car save successful";
    }

    @Override
    public String updateCarToCategory(String name, Car oldCar, Car newCar) {
        Category category = this.findByName(name);
        Set<Car> cars = category.getCars();
        if (!cars.remove(oldCar) || !cars.add(newCar)) {
            throw new EntityException("Car update failed");
        }
        category.setCars(cars);
        try {
            categoryReposity.saveAndFlush(category);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
        }
        return "Car update successful";
    }

    @Override
    public String delete(Long id) {
        try {
            categoryReposity.deleteById(id);
        } catch (Exception e) {
            logger.error("Category Exception: ", e);
            throw new EntityException("Category delete failed");
        }
        return "Category delete successful";
    }

    @Override
    public Category findById(Long id) {
        return categoryReposity.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public Category findBySlug(String slug) {
        return categoryReposity.findBySlug(slug).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public Category findBySlugForGuest(String slug) {
        return categoryReposity.findBySlugAndStatusIs(slug, Status.ENABLE).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public Page<Category> findAllPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return categoryReposity.findAll(pageable);
    }

    @Override
    public Page<Category> findAllPaginatedForGuest(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return categoryReposity.findAllByStatusIsAndParentNotNull(pageable, Status.ENABLE);
    }

    @Override
    public Boolean existsByName(String name) {
        return categoryReposity.existsByName(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryReposity.findByName(name).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public List<Category> findAllEnable() {
        return categoryReposity.findALlByStatusAndParentIsNotNull(Status.ENABLE);
    }

    @Override
    public Page<Category> search(int pageNo, int pageSize, String s) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,Sort.by(Sort.Order.asc("id")));
        return categoryReposity.search(s, pageable);
    }
}
