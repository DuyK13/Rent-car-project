package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.CategoryRequest;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Category;
import org.springframework.data.domain.Page;

import java.util.stream.DoubleStream;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 12:51 PM
 */
public interface ICategoryService {
    String save(CategoryRequest categoryRequest);

    String update(Long id, CategoryRequest categoryRequest);

    String update(Long id);

    String addCarToCategory(String name, Car car);

    String updateCarToCategory(String name, Car oldCar, Car newCar);

    String delete(Long id);

    Category findById(Long id);

    Page<Category> findAllPaginated(int pageNo);

    Boolean existsByName(String name);

    Category findByName(String name);

    Category findBySlug(String var);

    Category findByCar(Car car);

    Page<Category> findAllPaginatedForGuest(int pageNo);

    Category findBySlugForGuest(String slug);
}
