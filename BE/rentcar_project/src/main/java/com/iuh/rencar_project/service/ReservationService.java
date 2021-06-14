package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.ReservationRequest;
import com.iuh.rencar_project.entity.Reservation;
import com.iuh.rencar_project.repository.ReservationRepository;
import com.iuh.rencar_project.service.template.IReservationService;
import com.iuh.rencar_project.utils.enums.ReservationState;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IReservationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 6/14/2021 7:48 PM
 */
@Service
public class ReservationService implements IReservationService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository reservationRepository;
    private final IReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, IReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public String save(ReservationRequest reservationRequest) {
        try {
            reservationRepository.saveAndFlush(reservationMapper.toEntity(reservationRequest));
        } catch (Exception ex) {
            logger.error("Reservation Error: ", ex);
            throw new EntityException("Save reservation failed", ex);
        }
        return "Save reservation successful";
    }

    @Override
    public String approvedReservation(Long id) {
        Reservation reservation = this.findById(id);
        reservation.setState(ReservationState.APPROVED);
        try {
            reservationRepository.saveAndFlush(reservation);
        } catch (Exception ex) {
            logger.error("Reservation Error: ", ex);
            throw new EntityException("Update reservation failed", ex);
        }
        return "Update reservation successful";
    }

    @Override
    public String cancelReservation(Long id) {
        Reservation reservation = this.findById(id);
        reservation.setState(ReservationState.CANCEL);
        try {
            reservationRepository.saveAndFlush(reservation);
        } catch (Exception ex) {
            logger.error("Reservation Error: ", ex);
            throw new EntityException("Cancel reservation failed", ex);
        }
        return "Cancel reservation successful";
    }

    @Override
    public String expiredReservation(Long id) {
        return null;
    }


    @Override
    public Page<Reservation> getPageByState(String state, String search, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return reservationRepository.search(search, Enum.valueOf(ReservationState.class, state), pageable);
    }

    private Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found reservation"));
    }
}
