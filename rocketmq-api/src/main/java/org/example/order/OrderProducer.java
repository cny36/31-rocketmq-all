package org.example.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步发送模式
 *
 * @author : chennengyuan
 */
public class OrderProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.247.5:9876");
        producer.start();

        //订单顺序必须是 创建-付款-推送-完成
        List<OrderStep> list = createList();

        for (OrderStep orderStep : list) {
            Message message = new Message("OrderTopic", orderStep.toString().getBytes("UTF-8"));
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Long orderId = (Long) o;
                    //同一orderId发送到同一队列
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, orderStep.getId());
            System.out.println("生产者发送消息成功，返回结果：" + sendResult);
        }
        producer.shutdown();
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
