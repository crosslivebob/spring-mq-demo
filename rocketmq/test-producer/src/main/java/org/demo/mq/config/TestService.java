package org.demo.mq.config;

import org.demo.mq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class TestService {
    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
