package com.cny.rocketmqspringboot.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 模拟生产者
 *
 * @author : chennengyuan
 */
@Slf4j
@RestController
@RequestMapping("/producer")
public class Producer {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /**
     * 普通消息
     *
     * @return
     */
    @GetMapping("/sendMsg")
    public String sendMsg() {
        rocketMQTemplate.convertAndSend("springBooptTopic", "hello world");
        return "send success";
    }


    /**
     * 同步消息
     *
     * @return
     */
    @GetMapping("/sendSyncMsg")
    public String sendSyncMsg() {
        SendResult sendResult = rocketMQTemplate.syncSend("SyncMsgTopic", "hello world");
        log.info("发送同步消息成功 {}", sendResult);
        return "send success";
    }


    /**
     * 异步消息
     *
     * @return
     */
    @GetMapping("/sendAsyncMsg")
    public String sendAsyncMsg() {
        rocketMQTemplate.asyncSend("AsyncMsgTopic", "hello world", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("onSuccess   {}", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("onException   {}", throwable.getMessage());
            }
        });
        log.info("发送异步消息成功");
        return "send success";
    }


    /**
     * 有序消息
     * 创建、付款、推送、完成
     *
     * @return
     */
    @GetMapping("/sendOrderMsg")
    public String sendOrderMsg() {
        List<OrderStep> list = createList();
        for (OrderStep orderStep : list) {
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("orderTopic", orderStep, String.valueOf(orderStep.getId()));
            log.info("发送有序消息成功 {}", sendResult);
        }
        return "send success";
    }


    /**
     * 延迟消息
     *
     * @return
     */
    @GetMapping("/sendScheduledMsg")
    public String sendScheduledMsg() {
        SendResult sendResult = rocketMQTemplate.syncSend("scheduledTopic",
                MessageBuilder.withPayload("scheduled msg").build(),
                1000,
                3);
        log.info("延迟消息发送结束 {} {}", new Date(), sendResult);
        return "send success";
    }


    /**
     * 批量消息
     *
     * @return
     */
    @GetMapping("/sendBatchMsg")
    public String sendBatchMsg() {
        List<Message> list = new ArrayList<>(3);
        list.add(new GenericMessage("11111"));
        list.add(new GenericMessage("22222"));
        list.add(new GenericMessage("33333"));

        SendResult sendResult = rocketMQTemplate.syncSend("batchTopic", list);
        log.info("批量消息发送结束 {} {}", sendResult);
        return "send success";
    }


    /**
     * 过滤消息
     *
     * @return
     */
    @GetMapping("/sendFilterMsgBySql")
    public String sendFilterMsgBySql() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 18);
        map.put("salary", 9000);
        MessageHeaders messageHeaders = new MessageHeaders(map);
        Message message = new GenericMessage("过滤消息BySql", messageHeaders);
        SendResult sendResult = rocketMQTemplate.syncSend("filterTopic", message);
        log.info("过滤消息 发送结束 {} {}", sendResult);
        return "send success";
    }

    /**
     * 过滤消息
     *
     * @return
     */
    @GetMapping("/sendFilterMsgByTag")
    public String sendFilterMsgByTag() {
        Message message = new GenericMessage("过滤消息ByTag");
        SendResult sendResult = rocketMQTemplate.syncSend("filterByTagTopic:tagA", message);
        log.info("过滤消息 发送结束 {} {}", sendResult);
        return "send success";
    }


    /**
     * 事务消息
     *
     * @return
     */
    @GetMapping("/sendTransactionMsg")
    public String sendTransactionMsg() {
        Message message = new GenericMessage("事务消息");
        SendResult sendResult = rocketMQTemplate.sendMessageInTransaction("transactionTopic", message, null);
        log.info("事务消息 发送结束 {} {}", sendResult);
        return "send success";
    }


    /**
     * 广播消息
     * 多个消费者都可消费
     *
     * @return
     */
    @GetMapping("/sendBroadcastMsg")
    public String sendBroadcastMsg() {
        Message message = new GenericMessage("sendBroadcastMsg");
        SendResult sendResult = rocketMQTemplate.syncSend("BroadcastMsgTopic", message);
        log.info("广播消息 发送结束 {} {}", sendResult);
        return "send success";
    }


    public static List<OrderStep> createList() {
        List<OrderStep> list = new ArrayList<>();
        list.add(OrderStep.builder().id(1L).desc("创建").build());
        list.add(OrderStep.builder().id(2L).desc("创建").build());
        list.add(OrderStep.builder().id(1L).desc("付款").build());
        list.add(OrderStep.builder().id(3L).desc("创建").build());
        list.add(OrderStep.builder().id(2L).desc("付款").build());
        list.add(OrderStep.builder().id(3L).desc("付款").build());
        list.add(OrderStep.builder().id(2L).desc("推送").build());
        list.add(OrderStep.builder().id(1L).desc("推送").build());
        list.add(OrderStep.builder().id(3L).desc("推送").build());
        list.add(OrderStep.builder().id(1L).desc("完成").build());
        list.add(OrderStep.builder().id(3L).desc("完成").build());
        list.add(OrderStep.builder().id(2L).desc("完成").build());
        return list;
    }
}
