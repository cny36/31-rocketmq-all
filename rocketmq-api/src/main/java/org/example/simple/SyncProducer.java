package org.example.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 同步发送模式
 *
 * @author : chennengyuan
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.247.5:9876");
        producer.start();

        Message message = new Message("OrderTopic", "同步发送模式 - 创建0001号订单".getBytes("UTF-8"));
        SendResult sendResult = producer.send(message);
        System.out.println("生产者发送消息成功，返回结果：" + sendResult);

        producer.shutdown();
    }
}
