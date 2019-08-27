
package com.shopp.activemq.test02;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer002 {

    public static void main(String[] args) throws JMSException {
        System.out.println("Consumer01");
        // 获取mq连接工程
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.3.100:61616");
        // 创建连接
        Connection createConnection = connectionFactory.createConnection();
        // 启动连接
        createConnection.start();
        // 创建会话工厂
        Session session = createConnection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 创建队列
        Destination destination = session.createTopic("shopp-topic");
        MessageConsumer createConsumer = session.createConsumer(destination);
        while (true) {
            // 监听消息
            TextMessage textMessage = (TextMessage) createConsumer.receive();
            if (textMessage != null) {
                String text = textMessage.getText();
                System.out.println("消费者 获取到消息:  text:" + text);

            } else {
                break;
            }
        }

        System.out.println("消费者消费消息完毕!!!");
    }
}
