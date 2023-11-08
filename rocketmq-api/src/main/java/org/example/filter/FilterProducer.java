package org.example.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 过滤消息发送模式
 *
 * @author : chennengyuan
 */
public class FilterProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.247.5:9876");
        producer.start();

        Message message1 = new Message("Filter", "create message".getBytes("UTF-8"));
        Message message2 = new Message("Filter", "update message".getBytes("UTF-8"));
        Message message3 = new Message("Filter", "delete message".getBytes("UTF-8"));

        SendResult sendResult1 = producer.send(message1);
        SendResult sendResult2 = producer.send(message2);
        SendResult sendResult3 = producer.send(message3);
        //System.out.println("生产者发送消息成功，" + new Date() + "，返回结果：" + sendResult);

        producer.shutdown();
    }
}
