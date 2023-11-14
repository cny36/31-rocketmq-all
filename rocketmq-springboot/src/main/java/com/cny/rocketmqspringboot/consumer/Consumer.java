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
//@RocketMQMessageListener(topic = "springBooptTopic", consumerGroup = "springbootgroup1")
//@RocketMQMessageListener(topic = "SyncMsgTopic", consumerGroup = "springbootgroup1")
//@RocketMQMessageListener(topic = "AsyncMsgTopic", consumerGroup = "springbootgroup1")
//@RocketMQMessageListener(topic = "orderTopic", consumerGroup = "orderTopic", consumeMode = ConsumeMode.ORDERLY)
//@RocketMQMessageListener(topic = "scheduledTopic", consumerGroup = "scheduledTopic")
//@RocketMQMessageListener(topic = "batchTopic", consumerGroup = "batchTopic")
//@RocketMQMessageListener(topic = "filterTopic", consumerGroup = "filterTopic", selectorType = SelectorType.SQL92, selectorExpression = "age<30 and salary>6000")
//@RocketMQMessageListener(topic = "filterByTagTopic", consumerGroup = "filterByTagTopic", selectorType = SelectorType.TAG, selectorExpression = "tagA")
//@RocketMQMessageListener(topic = "transactionTopic", consumerGroup = "transactionTopic")
@RocketMQMessageListener(topic = "BroadcastMsgTopic", consumerGroup = "BroadcastMsgTopic", messageModel = MessageModel.BROADCASTING)
public class Consumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        log.info("{} 消费者收到消息：{}", new Date(), s);
    }
}
