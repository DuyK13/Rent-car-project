/**
 * @author DuyTT10
 * @date Mar 23, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.repotitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.rencarproject.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	boolean existsByIdAndStatusEquals(Long id, int status);
	
	Course findByIdAndStatusEquals(Long id, int status);
}
