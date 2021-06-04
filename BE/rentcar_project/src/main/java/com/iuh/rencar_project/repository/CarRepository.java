package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.utils.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findBySlug(String slug);

    Boolean existsByName(String name);

    Optional<Car> findByName(String name);

    Optional<Car> findBySlugAndStatus(String slug, Status enable);

    List<Car> findAllByStatus(Status status);

    @Query(value = "SELECT c FROM Car c WHERE CONCAT(c.name, ' ', c.licensePlate, ' ', c.createdBy.username, ' ', c.modifiedBy.username, ' ', c.createdDate, ' ', c.modifiedDate, ' ') LIKE %?1%")
    Page<Car> search(String s, Pageable pageable);
}
