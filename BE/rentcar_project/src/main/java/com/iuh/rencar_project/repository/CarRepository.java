package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findBySlug(String slug);

    Boolean existsByName(String name);

    Optional<Car> findByName(String name);
}
