package org.demo.mq.service;

import org.demo.mq.mapper.StudentMapper;
import org.demo.mq.pojo.Student;
import org.demo.mq.pojo.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
//@EnableConfigurationProperties(MybatisProperties.class)
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StuTestService stuTestService;

    @Autowired
    private TestProperties testProperties;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Student> queryStudent() {
//        System.out.println(testProperties);
//        //测试事务传播
//        stuTestService.testInsert();
//        try {
//            stuTestService.testUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        if (1 == 1) {
//            throw new RuntimeException("Test Exception!!!");
//        }
        return studentMapper.queryStudent();
    }
}
