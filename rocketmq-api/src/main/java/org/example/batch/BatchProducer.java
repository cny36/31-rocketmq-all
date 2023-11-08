package org.example.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 批量消息发送模式
 *
 * @author : chennengyuan
 */
public class BatchProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.247.5:9876");
        producer.start();

        Message message1 = new Message("BatchTopic", "这是一条批量消息1".getBytes("UTF-8"));
        Message message2 = new Message("BatchTopic", "这是一条批量消息2".getBytes("UTF-8"));
        Message message3 = new Message("BatchTopic", "这是一条批量消息3".getBytes("UTF-8"));
        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        SendResult sendResult = producer.send(messages);
        System.out.println("生产者发送消息成功，" + new Date() + "，返回结果：" + sendResult);

        producer.shutdown();
    }
}
