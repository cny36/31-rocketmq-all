package org.example.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 异步发送模式
 *
 * @author : chennengyuan
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.247.5:9876");
        producer.start();

        Message message = new Message("OrderTopic", "异步发送模式 - create order 00001".getBytes("UTF-8"));
        producer.send(message, new SendCallback() {
            public void onSuccess(SendResult sendResult) {
                System.out.println("生产者发送消息成功，返回结果：" + sendResult);
            }

            public void onException(Throwable throwable) {
                System.out.println("生产者发送消息失败，原因：" + throwable);
            }
        });

        System.out.println("异步消息发送完成");

        System.out.println("模拟处理其他业务逻辑...");
    }
}
