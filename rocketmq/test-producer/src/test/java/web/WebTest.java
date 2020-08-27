package web;

import org.demo.mq.TestProducerApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootTest(classes = TestProducerApplication.class)
public class WebTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testWeb() {
        DispatcherServlet dispatcherServlet;
        System.out.println(restTemplate);
    }
}
