package org.demo.mq.service;

import org.demo.mq.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuTestService {
    @Autowired
    private StudentMapper studentMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void testInsert() {
        studentMapper.insertStudent(111L, "test", "test");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void testUpdate() {
//        studentMapper.updateStudent("嘻嘻", 222L);
        studentMapper.insertStudent(222L, "嘻嘻", "嘻嘻");
//        if (1 == 1) {
//            throw new RuntimeException("Test Exception!!!");
//        }
    }
}
