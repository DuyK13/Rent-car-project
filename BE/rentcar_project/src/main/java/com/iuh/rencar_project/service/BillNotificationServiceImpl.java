package com.iuh.rencar_project.service;

import com.iuh.rencar_project.service.template.INotificationService;
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
public class BillNotificationServiceImpl implements INotificationService {

    private final List<SseEmitter> emitters = new ArrayList<>();

    @Override
    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
    }

    @Override
    public void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
    }

    @Async
    @Override
    public void doNotify() {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name("Pre-order").data("Pre-orders just placed"));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        });
        emitters.removeAll(deadEmitters);
    }
}
