package com.cny.rocketmqspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringEventTest {

    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;

    /**
     * 基于SpringEvent实现发布订阅模式
     */
    @Test
    void contextLoads() {
        customSpringEventPublisher.publishEvent("你好");
    }

}
