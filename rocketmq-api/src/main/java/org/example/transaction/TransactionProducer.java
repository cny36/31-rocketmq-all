package org.example.transaction;

import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * 事务消息
 *
 * @author : chennengyuan
 */
public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("group2");
        transactionMQProducer.setNamesrvAddr("192.168.247.5:9876");
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 100,
                TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("transaction-msg-thread");
                return thread;
            }
        });
        transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(new TransactionListenerImpl());
        transactionMQProducer.start();

        Message message = new Message("transaction_topic", "transaction msg".getBytes(StandardCharsets.UTF_8));
        TransactionSendResult transactionSendResult = transactionMQProducer.sendMessageInTransaction(message, null);
        System.out.println(transactionSendResult);
    }

}
