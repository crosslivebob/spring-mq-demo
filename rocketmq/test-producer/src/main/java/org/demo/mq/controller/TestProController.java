package org.demo.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.demo.mq.mapper.StudentMapper;
import org.demo.mq.pojo.Student;
import org.demo.mq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("producer")
public class TestProController {
    @Autowired
    StudentService studentService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping("sync/{mq}")
    public ResponseEntity<String> producer(@PathVariable("mq") String mq) {
        try {
            // 实例化消息生产者Producer
             DefaultMQProducer producer = new DefaultMQProducer("test_mq_sync");
            // 设置NameServer的地址
            producer.setNamesrvAddr("localhost:9876");
            // 启动Producer实例
            producer.start();

            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + mq).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            log.info("" + sendResult);

            // 如果不再发送消息，关闭Producer实例。
            producer.shutdown();

        } catch (Exception e){
            log.error("error:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("sucess");
    }

    @GetMapping("test")
    public List<Student> test(HttpServletRequest request1) {
        List<Student> list = studentService.queryStudent();
        System.out.println(request.getHeader("test"));
        return list;
    }

    @GetMapping("str")
    public String str(@RequestParam("test") String test) {
        return test + "-222";
    }


    @GetMapping("/insertstu")
    @Transactional(rollbackFor = Exception.class)
    public void testInsert() {
        studentMapper.insertStudent(444L, "test", "test");
        try {
            studentService.testUpdate();
        } catch (Exception e) {
            System.out.println("测试");
        }
//        throw new RuntimeException("33");
    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public void testUpdate() {
//        try {
            studentMapper.insertStudent(20180101L, "test", "test");
//            studentMapper.updateStudent("testUpdate", 111L);
//        } catch (Exception e) {
//            System.out.println("测试");
//        }
    }
}
