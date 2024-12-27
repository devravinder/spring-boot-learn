package com.paravar;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppEventListener {
    @EventListener
    public void handleAppEvent(AppEvent event) {
        System.out.println("message: " + event.getMessage() + " from: " + event.getFrom());
    }
}
