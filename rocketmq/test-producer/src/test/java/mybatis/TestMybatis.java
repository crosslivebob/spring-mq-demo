package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.demo.mq.TestProducerApplication;
import org.demo.mq.mapper.StudentMapper;
import org.demo.mq.pojo.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestProducerApplication.class)
public class TestMybatis {

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private SqlSessionFactory factory;

    @Test
    public void testMapper() {
        System.out.println(mapper.queryStudent());
    }

    @Test
    public void testLocalCache() {
        //测试一级缓存，最好关闭二级缓存
        SqlSession sqlSession = factory.openSession();

        StudentMapper stuMapper = sqlSession.getMapper(StudentMapper.class);
        StudentMapper stuMapper1 = sqlSession.getMapper(StudentMapper.class);

        Student stu = stuMapper.queryStudentByNumber(20180101L);
        System.out.println(stu);
        Student stu1 = stuMapper1.queryStudentByNumber(20180101L);
        System.out.println(stu1);
    }

    @Test
    public void testCache() {
        //测试二级缓存
        SqlSession sqlSession = factory.openSession();
        SqlSession sqlSession1 = factory.openSession();

        StudentMapper stuMapper = sqlSession.getMapper(StudentMapper.class);
        StudentMapper stuMapper1 = sqlSession1.getMapper(StudentMapper.class);

        Student stu = stuMapper.queryStudentByNumber(20180101L);
        System.out.println(stu);
        sqlSession.commit();
        Student stu1 = stuMapper1.queryStudentByNumber(20180101L);
        System.out.println(stu1);
    }
}
