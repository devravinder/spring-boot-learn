package com.paravar;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NotificationEvent extends ApplicationEvent {
    private final String message;

    public NotificationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}