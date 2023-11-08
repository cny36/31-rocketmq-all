package org.example.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;

/**
 * @author : chennengyuan
 */
public class TransactionListenerImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println(new Date() + " TransactionListenerImpl executeLocalTransaction");
        return LocalTransactionState.ROLLBACK_MESSAGE;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        System.out.println(new Date() + " TransactionListenerImpl checkLocalTransaction");
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
