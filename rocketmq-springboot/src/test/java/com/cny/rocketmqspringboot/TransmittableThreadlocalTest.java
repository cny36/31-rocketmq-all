package com.cny.rocketmqspringboot;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransmittableThreadlocalTest {

    private static final ThreadLocal<String> requestIdTL = new TransmittableThreadLocal<>();

    @Test
    void contextLoads() {
        requestIdTL.set("123456");

        new Thread(() -> {
            System.out.println(requestIdTL.get()); // 输出：null
        }).start();
    }

}
