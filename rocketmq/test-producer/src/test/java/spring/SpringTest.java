package spring;

import org.junit.Test;
import org.springframework.aop.framework.AopProxy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import spi.TestDeveloper;
import spi.model.Developer;
import spring.model.Student;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class SpringTest {

    @Autowired
    Student student;

    @Test
    public void testBeanFactory() {
//        Resource resource = new FileSystemResource("src/test/resources/test.xml");
        Resource resource = new ClassPathResource("test.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);

//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions(resource);

        Student s = (Student) beanFactory.getBean("student");
        Student s1 = (Student) beanFactory.getBean("student");
        System.out.println(s==s1);
    }

    @Test
    public void testXml() {
//        Resource resource = new FileSystemResource("src/test/resources/test.xml");
        Resource resource = new ClassPathResource("test.xml");
        GenericApplicationContext ctx = new GenericXmlApplicationContext(resource);
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("spring");
        Student s = (Student) ctx.getBean("student");
        s.test();
        System.out.println(s);
    }

    @Test
    public void testAutoeired() {
        //classPath搜索
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");

        //文件地址搜索
//        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("src/test/resources/test.xml");

        Student s = (Student) ctx.getBean("student");
        System.out.println(s);

        Developer developer = new TestDeveloper();
        Object object = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), developer.getClass().getInterfaces(),
                (o, method, objects) -> {
                    System.out.println("test start!!");
                    Object rs = method.invoke(developer, objects);
                    return rs;
                });
        ((Developer)object).sayHi();
    }

    @Test
    public void testLiteMode() {
        //classPath搜索
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");

        //文件地址搜索
//        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("src/test/resources/test.xml");

        Student s = (Student) ctx.getBean("student");
        s.test();
    }

}
