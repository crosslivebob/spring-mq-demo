package spring.event;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("test.xml");
    }

    @Test
    public void publishTest() {
        EventDemo eventDemo = new EventDemo("This is a source!");
        eventDemo.setMessage("hello message!");
        applicationContext.publishEvent(eventDemo);
    }
}
