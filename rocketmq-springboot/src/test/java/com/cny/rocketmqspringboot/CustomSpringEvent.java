package com.cny.rocketmqspringboot;

import org.springframework.context.ApplicationEvent;

/**
 * @author : chennengyuan
 */
public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
