package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.service.BillNotificationServiceImpl;
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

    private final BillNotificationServiceImpl billNotificationService;

    @Autowired
    public NotificationController(BillNotificationServiceImpl billNotificationService) {
        this.billNotificationService = billNotificationService;
    }

    @RequestMapping(value = "/subcribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter billSubcribe() {
        SseEmitter sseEmiter = new SseEmitter(Long.MAX_VALUE);
        this.sendInitEvent(sseEmiter);
        billNotificationService.addEmitter(sseEmiter);
        sseEmiter.onCompletion(() -> billNotificationService.removeEmitter(sseEmiter));
        sseEmiter.onTimeout(() -> billNotificationService.removeEmitter(sseEmiter));
        sseEmiter.onError((e) -> billNotificationService.removeEmitter(sseEmiter));
        return sseEmiter;
    }

    @PostMapping(value = "/bill/notifyAll")
    public void billNotifyAll(){
        billNotificationService.doNotify();
    }

    private void sendInitEvent(SseEmitter sseEmiter) {
        try {
            sseEmiter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
