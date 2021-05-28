package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.utils.enums.BillState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findBySlug(String var);

    List<Bill> findAllByState(BillState pre_order);

    Boolean existsByIdAndState(Long id, BillState pre_order);

    void deleteByIdAndStateIs(Long id, BillState pre_order);

    Bill findByIdAndStateIs(Long id, BillState pre_order);
}
