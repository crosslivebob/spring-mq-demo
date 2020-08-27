package org.demo.mq;

import org.demo.mq.initializer.TestApplicationContextInitializer;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.PrintStream;

@SpringBootApplication
public class TestProducerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(TestProducerApplication.class);
        application.addInitializers(new TestApplicationContextInitializer());
        application.run(args);
//        SpringApplication.run(TestProducerApplication.class, args);
    }

//    @PostConstruct
//    public void testPPP() {
//        System.out.println("this is a testPPP");
//    }
}
