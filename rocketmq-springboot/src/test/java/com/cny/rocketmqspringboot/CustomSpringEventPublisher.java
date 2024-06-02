package com.cny.rocketmqspringboot;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author : chennengyuan
 */
@Component
public class CustomSpringEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发布事件
     *
     * @param message
     */
    public void publishEvent(String message) {
        applicationEventPublisher.publishEvent(new CustomSpringEvent(this, message));
    }
}
