package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CategoryRequest;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Category;
import com.iuh.rencar_project.repository.CategoryReposity;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.ICarMapper;
import com.iuh.rencar_project.utils.mapper.ICategoryMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    private final ICarMapper carMapper;

    @Autowired
    public CategoryServiceImpl(CategoryReposity categoryReposity, ICategoryMapper categoryMapper, ICarMapper carMapper) {
        this.categoryReposity = categoryReposity;
        this.categoryMapper = categoryMapper;
        this.carMapper = carMapper;
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
        Category currentCategory = this.findById(id);
        String currentName = currentCategory.getName();
        if (this.existsByName(categoryRequest.getName()) && !currentName.equals(categoryRequest.getName()))
            throw new EntityException("Category " + categoryRequest.getName() + " exists");
        try {
            categoryMapper.updateEntity(categoryRequest, currentCategory);
            categoryReposity.saveAndFlush(currentCategory);
        } catch (Exception e) {
            logger.error("Category Exception: ", e);
            throw new EntityException("Category " + currentName + " update fail");
        }
        return "Category " + currentName
                + " update success";
    }

    @Override
    public String update(Long id) {
        Category currentCategory = this.findById(id);
        String name = currentCategory.getName();
        String message;
        try {
            Status status = currentCategory.getStatus();
            if (Status.ACTIVE.equals(status)) {
                currentCategory.setStatus(Status.INACTIVE);
                categoryReposity.saveAndFlush(currentCategory);
                message = "Inactive success";
            } else {
                currentCategory.setStatus(Status.ACTIVE);
                categoryReposity.saveAndFlush(currentCategory);
                message = "Active success";
            }
        }catch (Exception e){
            logger.error("Category Exception: ", e);
            throw new EntityException("Change status fail");
        }
        return message;
    }

    @Override
    public String addCarToCategory(String name, Car car) {
        Category category = this.findByName(name);
        try {
            Set<Car> cars = category.getCars();
            if (!cars.add(car))
                throw new EntityException("Car " + car.getName() + " save fail");
            category.setCars(cars);
            categoryReposity.saveAndFlush(category);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
        }
        return "Car " + car.getName() + " save success";
    }

    @Override
    public String updateCarToCategory(String name, Car oldCar, Car newCar) {
        Category category = this.findByName(name);
        try {
            Set<Car> cars = category.getCars();
            cars.remove(oldCar);
            cars.add(newCar);
            category.setCars(cars);
            categoryReposity.saveAndFlush(category);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
        }
        return "Car " + newCar.getName() + " save success";
    }

    @Override
    public String delete(Long id) {
        Category category = this.findById(id);
        String name = category.getName();
        try {
            categoryReposity.deleteById(id);
        } catch (Exception e) {
            logger.error("Category Exception: ", e);
            throw new EntityException("Category " + name + " delete fail");
        }
        return "Category " + name + " delete success";
    }

    @Override
    public Category findById(Long id) {
        return categoryReposity.findById(id).orElseThrow(() -> new NotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public Category findBySlug(String slug) {
        return categoryReposity.findBySlug(slug).orElseThrow(() -> new NotFoundException("Category with slug " + slug + " not found"));
    }

    @Override
    public Category findBySlugForGuest(String slug) {
        return  categoryReposity.findBySlugAndStatusIs(slug, Status.ACTIVE).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public Page<Category> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return categoryReposity.findAll(pageable);
    }

    @Override
    public Page<Category> findAllPaginatedForGuest(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return categoryReposity.findAllByStatusIs(pageable, Status.ACTIVE);
    }

    @Override
    public Boolean existsByName(String name) {
        return categoryReposity.existsByName(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryReposity.findByName(name).orElseThrow(() -> new NotFoundException("Category with name " + name + " not found"));
    }

    @Override
    public Category findByCar(Car car) {
        return categoryReposity.findByCarsIsContaining(car).orElseThrow(() -> new NotFoundException("Category with car name " + car.getName() + " not found"));
    }
}
