package spring.model;

import org.springframework.context.annotation.Bean;

public class TestBean {

    public TestBean() {
        System.out.println("我被调用了~~~");
    }

    @Bean
    public Student testStu() {
        return new Student();
    }
}
