package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.utils.enums.BillState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

//    @Query(value = "SELECT * FROM bills b WHERE MATCH (b.fullname, b.phone_number, b.email) AGAINST ('trantheduyk13') AND b.state = ?1", countQuery = "SELECT * FROM bills b WHERE MATCH (b.fullname, b.phone_number, b.email) AGAINST ('trantheduyk13') AND b.state = ?1", nativeQuery = true)
//    Page<Bill> search(String text, String state, Pageable pageable);

    @Query(value = "SELECT * FROM bills b WHERE year(b.modified_date) = ?2 AND month(b.modified_date) = ?1", nativeQuery = true)
    List<Bill> findAllByMonthAndYear(int month, int year);

    @Query(value = "SELECT b FROM Bill b WHERE CONCAT(b.fullname, ' ', b.phoneNumber, ' ', b.email, ' ') LIKE %?1% AND b.state = ?2")
    Page<Bill> search(String text, BillState state, Pageable pageable);
}
