package spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import spring.model.Student;

import javax.annotation.PostConstruct;

@Component
public class InitBeanTest implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    Student student;

    public InitBeanTest() {
        System.err.println("----> InitSequenceBean: constructor: "+ student);
    }

    @PostConstruct
    public void postConstruct() {
        System.err.println("----> InitSequenceBean: @PostConstruct: "+ student);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("----> InitSequenceBean: afterPropertiesSet: "+ student);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        System.err.println("----> InitSequenceBean: onApplicationEvent");
    }
}
