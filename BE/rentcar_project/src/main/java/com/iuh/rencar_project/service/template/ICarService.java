package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.CarRequest;
import com.iuh.rencar_project.entity.Car;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 2:29 PM
 */
public interface ICarService {

    String save(CarRequest carRequest);

    String update(Long id, CarRequest carRequest);

    String delete(Long id);

    Car findBySlug(String slug);

    Car findById(Long id);

    Boolean existsByName(String name);
}