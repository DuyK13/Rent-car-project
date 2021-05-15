package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.CategoryRequest;
import com.iuh.rencar_project.entity.Category;
import org.springframework.data.domain.Page;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 12:51 PM
 */
public interface ICategoryService {
    String save(CategoryRequest categoryRequest);

    String update(Long id, CategoryRequest categoryRequest);

    String delete(Long id);

    Category findById(Long id);

    Page<Category> findAllPaginated(int pageNo);

    Boolean existsByName(String name);

    Category findByName(String name);

    Category findBySlug(String var);
}
