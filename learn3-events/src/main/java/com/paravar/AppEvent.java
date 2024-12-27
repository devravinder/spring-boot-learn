package com.paravar;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class AppEvent extends ApplicationEvent {

    private final String message;
    private final String from;



    public AppEvent(Object source, String message, String from) {
        super(source);
        this.message = message;
        this.from = from;
    }
}