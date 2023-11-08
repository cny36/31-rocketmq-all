package org.example.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;

/**
 * 延迟消息发送模式
 *
 * @author : chennengyuan
 */
public class DelayProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.247.5:9876");
        producer.start();

        Message message = new Message("ScheduledTopic", "这是一条延迟消息".getBytes("UTF-8"));
        message.setDelayTimeLevel(3);
        SendResult sendResult = producer.send(message);
        System.out.println("生产者发送消息成功，" + new Date() + "，返回结果：" + sendResult);

        producer.shutdown();
    }
}
