package com.iuh.rencar_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.rencar_project.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
