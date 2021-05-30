package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.utils.enums.BillState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findBySlug(String var);

    Page<Bill> findAllByStateIs(Pageable pageable, BillState state);

    Boolean existsByIdAndState(Long id, BillState state);

    void deleteByIdAndStateIs(Long id, BillState state);

    Bill findByIdAndStateIs(Long id, BillState state);
}
