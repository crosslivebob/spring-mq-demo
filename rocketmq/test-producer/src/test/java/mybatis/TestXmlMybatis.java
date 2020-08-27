package mybatis;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import mybatis.model.TestDemo;
import mybatis.model.TestMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.mq.mapper.StudentMapper;
import org.demo.mq.pojo.Student;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class TestXmlMybatis {
    @Test
    public void tesSessionFactoryBuilder() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory testFactory = new SqlSessionFactoryBuilder().build(reader);
        // 4.获取Session
        SqlSession sqlSession = testFactory.openSession(false);
        // 5.操作Mapper接口
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        List<TestDemo> demos = mapper.queryTest();

        sqlSession.commit();
        System.out.println(demos);
    }

    @Test
    public void test() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        File file = new File("D:\\11.class");
        FileOutputStream stream = new FileOutputStream(file);
        Class<?> clazz = Class.forName("mybatis.model.TestDemo");
        stream.write(testCC(clazz.newInstance()));
        stream.close();
    }

    public byte[] testCC(Object clazz) throws IOException {
        ByteOutputStream os = new ByteOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        objectOutputStream.writeObject(clazz);
        return os.getBytes();
    }
}
