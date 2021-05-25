package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.CarRequest;
import com.iuh.rencar_project.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 2:29 PM
 */
public interface ICarService {

    String save(CarRequest carRequest, MultipartFile multipartFile);

    String update(Long id, CarRequest carRequest, MultipartFile multipartFile);

    String delete(Long id);

    Car findBySlug(String slug);

    Car findByName(String name);

    Car findById(Long id);

    Boolean existsByName(String name);

    Page<Car> findAllPaginated(int pageNo);

    Car findByCategorySlugAndCarSlugForGuest(String categorySlug, String carSlug);
}
