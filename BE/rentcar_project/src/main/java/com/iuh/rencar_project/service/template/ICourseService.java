package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.CourseRequest;
import com.iuh.rencar_project.dto.response.CourseResponse;
import com.iuh.rencar_project.entity.Course;
import com.iuh.rencar_project.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:06 AM
 */
public interface ICourseService {
    String save(CourseRequest courseRequest);

    String update(Long id, CourseRequest courseRequest);

    String setAvailability(Long id);

    String delete(Long id);

    Boolean existsByTitle(String title);

    Page<Course> findAllPaginated(int pageNo);

    Page<Course> findAllPaginatedForGuest(int pageNo);

    List<Course> findAllForGuest(int pageNo);

    List<Course> findAll();

    Course findBySlug(String slug);

    Course findByTitle(String title);

    Course findById(Long id);

    List<Course> findAllEnable();
}
