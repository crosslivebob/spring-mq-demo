package org.demo.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RestController
@RequestMapping("consumer")
public class TestConsController {

    @PostMapping("start")
    public ResponseEntity<String> consumer() {
        try {
            // 实例化消费者
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_mq_sync");
            // 设置NameServer的地址
            consumer.setNamesrvAddr("localhost:9876");

            // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
            consumer.subscribe("TopicTest", "*");

            // 注册回调实现类来处理从broker拉取回来的消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    log.info(Thread.currentThread().getName() + "Receive New Messages: " + msgs);
                    msgs.forEach(m -> {
                        log.info("body:" + m.getBody());
                    });
                    // 标记该消息已经被成功消费
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            // 启动消费者实例
            consumer.start();
            log.info("Consumer Started");
        } catch (Exception e){
            log.error("error:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("over");
    }

}
