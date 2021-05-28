package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CourseRequest;
import com.iuh.rencar_project.entity.Course;
import com.iuh.rencar_project.repository.CourseRepository;
import com.iuh.rencar_project.service.template.ICourseService;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.ICourseMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:08 AM
 */
@Service
public class CourseServiceImpl implements ICourseService {

    private static final Logger logger = LogManager.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final ICourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ICourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public String save(CourseRequest courseRequest) {
        String title = courseRequest.getTitle();
        if (this.existsByTitle(title))
            throw new EntityException("Course exists");
        try {
            courseRepository.saveAndFlush(courseMapper.toEntity(courseRequest));
        } catch (Exception e) {
            logger.error("Course Exception: ", e);
            throw new EntityException("Course save fail", e);
        }
        return "Course save success";
    }

    @Override
    public String update(Long id, CourseRequest courseRequest) {
        Course currentCourse = this.findById(id);
        if (this.existsByTitle(courseRequest.getTitle()) && !currentCourse.getTitle().equals(courseRequest.getTitle()))
            throw new EntityException("Course exists");
        try {
            courseMapper.updateEntity(courseRequest, currentCourse);
            courseRepository.saveAndFlush(currentCourse);
        } catch (Exception e) {
            logger.error("Course Exception: ", e);
            throw new EntityException("Course update fail");
        }
        return "Course update success";
    }

    @Override
    public String update(Long id) {
        Course currentCourse = this.findById(id);
        String message;
        if (currentCourse.getStatus() == Status.ACTIVE) {
            currentCourse.setStatus(Status.INACTIVE);
            message = "Course deactivate success";
        } else {
            currentCourse.setStatus(Status.ACTIVE);
            message = "Course activate success";
        }
        try {
            courseRepository.saveAndFlush(currentCourse);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("Course change status fail");
        }
        return message;
    }


    @Override
    public String delete(Long id) {
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Course Exception: ", e);
            throw new EntityException("Course delete fail");
        }
        return "Course delete success";
    }

    @Override
    public Boolean existsByTitle(String title) {
        return courseRepository.existsByTitle(title);
    }

    @Override
    public Course findBySlug(String slug) {
        return courseRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    @Override
    public Course findByTitle(String title) {
        return courseRepository.findByTitle(title).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public Page<Course> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return courseRepository.findAll(pageable);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
