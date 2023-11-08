package org.example.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 单向发送模式
 *
 * @author : chennengyuan
 */
public class OneWayProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.247.5:9876");
        producer.start();

        Message message = new Message("OrderTopic", "这是一条单向消息".getBytes("UTF-8"));
        producer.sendOneway(message);

        producer.shutdown();
    }
}
