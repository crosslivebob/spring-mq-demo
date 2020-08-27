package redis;

import org.demo.mq.TestProducerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestProducerApplication.class)
public class TestSpringRedis {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void testStr() {
        //测试 String
        ValueOperations valueOperations = redisTemplate.opsForValue();
        System.out.println(valueOperations.get("mykey"));
    }

    @Test
    public void testHash() {
        //测试 hash
        BoundHashOperations testhash = redisTemplate.boundHashOps("testhash");
        testhash.put("phone", "8888-8888");
        System.out.println(testhash.entries());
    }
}
