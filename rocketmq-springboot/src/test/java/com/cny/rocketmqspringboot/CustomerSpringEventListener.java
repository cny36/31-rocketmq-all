package com.cny.rocketmqspringboot;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author : chennengyuan
 */
@Component
public class CustomerSpringEventListener {

    /**
     * 监听事件
     *
     * @param event
     */
    @EventListener
    public void handleCustomSpringEvent(CustomSpringEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }
}
