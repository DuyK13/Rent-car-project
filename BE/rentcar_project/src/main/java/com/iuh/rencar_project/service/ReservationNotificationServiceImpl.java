package com.iuh.rencar_project.service;

import com.iuh.rencar_project.service.template.IReservationNotificationService;
import com.iuh.rencar_project.utils.enums.ReservationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 5/28/2021 10:47 AM
 */
@Service
public class ReservationNotificationServiceImpl implements IReservationNotificationService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationNotificationServiceImpl.class);

    private final List<SseEmitter> emitters = new ArrayList<>();

    @Override
    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
    }

    @Override
    public void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
    }

    @Override
    public void doPendingNotify() {
        doNotify(ReservationState.PENDING.name(), "New reservation");
    }

    @Override
    public void doExpiredNotify() {
        doNotify(ReservationState.EXPIRED.name(), "Expired reservation");
    }

    @Async
    void doNotify(String name, String data) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name(name).data(data));
            } catch (IOException e) {
                logger.error("Notification Exception: ", e);
                deadEmitters.add(emitter);
            }
        });
        emitters.removeAll(deadEmitters);
    }
}
