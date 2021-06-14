package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.service.template.IReservationNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 8:47 AM
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final IReservationNotificationService reservationNotificationService;

    @Autowired
    public NotificationController(IReservationNotificationService reservationNotificationService) {
        this.reservationNotificationService = reservationNotificationService;
    }

//    @RequestMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
//    public SseEmitter billSubscribe() {
//        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
//        this.sendInitEvent(sseEmitter);
//        billNotificationService.addEmitter(sseEmitter);
//        sseEmitter.onCompletion(() -> billNotificationService.removeEmitter(sseEmitter));
//        sseEmitter.onTimeout(() -> billNotificationService.removeEmitter(sseEmitter));
//        sseEmitter.onError((e) -> billNotificationService.removeEmitter(sseEmitter));
//        return sseEmitter;
//    }
//
//    @PostMapping(value = "/bill/notifyAll")
//    public void billNotifyAll() {
//        billNotificationService.doNotify();
//    }
//
//    private void sendInitEvent(SseEmitter sseEmitter) {
//        try {
//            sseEmitter.send(SseEmitter.event().name("INIT"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
