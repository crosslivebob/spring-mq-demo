package org.demo.mq.mapper;


import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@CacheNamespace
public interface TestMapper {

    @Select("select * from test")
    public List<TestDemo> queryTest();
}
