package mybatis;

import mybatis.model.TestMapper;
import mybatis.model.TestStuMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringMybatis {
    @Test
    public void testSpringMybatis() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");

//        TestMapper mapper = (TestMapper) ctx.getBean("testMapper");
//        System.out.println(mapper.queryTest());

        //todo 扫描的包下面的接口都会被扫描进去，变成MapperFactory~~~~~
        TestStuMapper stuMapper = (TestStuMapper) ctx.getBean("testStuMapper");
//        System.out.println(stuMapper.queryStudent());
        System.out.println(stuMapper.selectByNumber(10086L));
//        stuMapper.insertStudent(111L, "tom", "数学");
    }
}
