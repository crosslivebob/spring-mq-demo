package spring.litemode;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FullMain {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LiteAppConfig.class);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        User user = context.getBean(User.class);
//        System.out.println(user.hashCode());
        context.close();
    }
}
