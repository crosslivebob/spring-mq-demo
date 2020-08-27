package mybatis.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.demo.mq.pojo.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface TestStuMapper {
    @Select("select * from student")
    public List<Student> queryStudent();

    @Insert("insert into student values(#{number}, #{name}, #{major})")
    public void insertStudent(Long n, String n1, String m);

    @Select("select * from student where number = #{number} and name = #{name}")
    public Student selectByNumber(Long n);
}
