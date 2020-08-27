package org.demo.mq.mapper;

import org.apache.ibatis.annotations.*;
import org.demo.mq.pojo.Student;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

@Mapper
//@DependsOn({"testMapper"})
//@CacheNamespace
@CacheNamespaceRef(TestMapper.class)
public interface StudentMapper {
    @Select("select * from student")
    public List<Student> queryStudent();

    @Insert("insert into student values(#{number}, #{name}, #{major})")
    public void insertStudent(@Param("number") Long number, @Param("name") String name, @Param("major") String major);

    @Select("select * from student where number=#{number}")
    public Student queryStudentByNumber(@Param("number") Long number);

    @Select("select * from student where number=#{number} for update")
    public Student queryStudentByNumberForUpdate(@Param("number") Long number);

    @Update("update student set name=#{name} where number=#{number}")
    public void updateStudent(@Param("name") String name, @Param("number") Long number);
}
