package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.ReservationRequest;
import com.iuh.rencar_project.entity.Reservation;
import com.iuh.rencar_project.repository.ReservationRepository;
import com.iuh.rencar_project.service.template.IEmailService;
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

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

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
    private final IEmailService emailService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, IReservationMapper reservationMapper, IEmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.emailService = emailService;
    }

    @Override
    public String save(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.toEntity(reservationRequest);
        try {
            reservation = reservationRepository.saveAndFlush(reservation);
        } catch (Exception ex) {
            logger.error("Reservation Error: ", ex);
            throw new EntityException("Save reservation failed", ex);
        }
        if (emailService.reservation(reservation))
            return "Save reservation successful and send mail";
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
            throw new EntityException("Approved reservation failed", ex);
        }
        this.expiredReservation(id);
        return "Approved reservation successful";
    }

    @Override
    public String cancelReservation(Long id) {
        try {
            reservationRepository.deleteById(id);
        } catch (Exception ex) {
            logger.error("Reservation Error: ", ex);
            throw new EntityException("Cancel reservation failed", ex);
        }
        return "Cancel reservation successful";
    }

    public void expiredReservation(Long id) {
        this.doExpired(this.findById(id));
    }


    @Override
    public Page<Reservation> getPageByState(String state, String search, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return state.isEmpty() ? reservationRepository.search(search, pageable) : reservationRepository.search(search, Enum.valueOf(ReservationState.class, state), pageable);
    }

    @Override
    public String remove(Long id) {
        try {
            reservationRepository.deleteById(id);
        } catch (Exception ex) {
            logger.error("Reservation Error: ", ex);
            throw new EntityException("Remove reservation failed", ex);
        }
        return "Remove reservation successful";
    }

    private Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found reservation"));
    }

    private synchronized void doExpired(Reservation reservation) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    reservationRepository.delete(reservation);
                } catch (Exception ex) {
                    logger.error("Reservation Error: ", ex);
                }
            }
        }, Timestamp.valueOf(reservation.getPickupDate()));
    }
}
