package com.iuh.rencar_project.service.template;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 8:42 AM
 */
public interface IReservationNotificationService {

    void addEmitter(SseEmitter emitter);

    void removeEmitter(SseEmitter emitter);

    void doPendingNotify();
}
