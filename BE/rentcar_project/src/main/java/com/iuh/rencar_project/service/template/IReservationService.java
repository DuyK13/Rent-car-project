package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.ReservationRequest;
import com.iuh.rencar_project.entity.Reservation;
import org.springframework.data.domain.Page;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 6/14/2021 9:40 AM
 */
public interface IReservationService {
    String save(ReservationRequest reservationRequest);

    String approvedReservation(Long id);

    String cancelReservation(Long id);

    String expiredReservation(Long id);

    Page<Reservation> getPageByState(String state, String search, int pageNo, int pageSize);
}
