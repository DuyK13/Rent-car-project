package com.iuh.rencar_project.repository;

import com.iuh.rencar_project.entity.Reservation;
import com.iuh.rencar_project.utils.enums.ReservationState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 6/14/2021 9:38 AM
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT r FROM Reservation r WHERE CONCAT(r.fullName, ' ', r.phoneNumber, ' ', r.email, ' ') LIKE %?1% AND r.state = ?2")
    Page<Reservation> search(String search, ReservationState state, Pageable pageable);
}
