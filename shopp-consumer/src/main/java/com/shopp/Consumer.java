
package com.shopp;

import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
@Component
public class Consumer {
    private int count = 0;

    @JmsListener(destination = "${queue}")
    public void receive(TextMessage textMessage, Session session) {
        try {
            String text = textMessage.getText();

            System.out.println("消费端接受到生长者消息: " + text + " 第几次获取消息 count:" + (++count));
            int i = 1 / 0;
            //伪代码耗时15秒
			Thread.sleep(15000L);
            String jmsMessageID = textMessage.getJMSMessageID();
            //网络延迟情况下，第二次消费过来，应该使用全局ID该消息是否被消费。
			//if(jmsMessageID==缓存里面){
				textMessage.acknowledge();// 避免第三次重试。
			//}
            // 消費成功
            //jmsMessageID==存放在緩存裡面


        } catch (Exception e) {
            // 在 catch 日志记录消息报文，可以采用补偿机制 （使用人工补偿），使用定时JOB健康检查脏数据。
            try {

                session.recover();// 重试。
            }catch (Exception ee){
            }
        }
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 1;
        Integer e = 127;
        Integer f = 127;
        Long g = 3L;
        System.out.println(++a);
        System.out.println(a);
        System.out.println(a==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
        StringBuffer sb = new StringBuffer();
        //SpringApplication.run(Consumer.class, args);
    }
}
