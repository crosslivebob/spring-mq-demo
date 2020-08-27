package org.demo.mq.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:test.properties")
public class TestProperties {
    private String testName;
    private Integer testAge;
    private String phone;
}
