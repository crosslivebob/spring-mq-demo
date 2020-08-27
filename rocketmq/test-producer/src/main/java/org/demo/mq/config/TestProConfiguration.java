package org.demo.mq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.beans.factory.annotation.Autowire.BY_TYPE;

@Configuration
public class TestProConfiguration {
    @Bean
//    @Bean(autowire = BY_TYPE) //整个按照类的所有参数都会按照类型注入
    public TestService testService() {
        return new TestService();
    }
}
