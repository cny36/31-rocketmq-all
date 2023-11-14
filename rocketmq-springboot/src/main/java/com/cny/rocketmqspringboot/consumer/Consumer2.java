package com.cny.rocketmqspringboot.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 模拟消费者
 *
 * @author : chennengyuan
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "BroadcastMsgTopic", consumerGroup = "BroadcastMsgTopic", messageModel = MessageModel.BROADCASTING)
public class Consumer2 implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        log.info("{} 消费者2收到消息：{}", new Date(), s);
    }
}
