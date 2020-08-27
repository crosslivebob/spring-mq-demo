package redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class TestRedis {
    private Jedis jedis;

    @Before
    public void setUp() {
        jedis = new Jedis("192.168.91.101", 6379);
    }

    @Test
    public void testStr() {
        //测试 String
        jedis.set("mykey", "testString");
        System.out.println(jedis.get("mykey"));
        //测试自增
        jedis.set("counter", "100");
        jedis.incr("counter");
        System.out.println(jedis.get("counter"));
    }

    @Test
    public void testHash() {
        //测试 hash
        Map<String, String> map = new HashMap<>();
        jedis.hset("testhash", "name", "张三");
        jedis.hset("testhash", "age", "25");
        jedis.hset("testhash", "sex", "boy");
        System.out.println(jedis.hgetAll("testhash"));
        jedis.hset("testhash", "address", "China");
        System.out.println(jedis.hget("testhash", "address"));
        //设置过期时间
//        jedis.expire("testhash", 2);
    }

    @Test
    public void testList() {
        //测试 list
        jedis.lpush("mylist", "d", "d1");
        System.out.println(jedis.lrange("mylist", 0 , -1));
        System.out.println(jedis.lpop("mylist"));
        System.out.println(jedis.lpop("mylist"));
    }

    @Test
    public void testSet() {
        //测试 set
        jedis.sadd("myset","e");
        System.out.println(jedis.smembers("myset"));
    }

    @Test
    public void testSortedSet() {
        //测试 sortedSet
        jedis.zadd("zset", 20, "d");
        jedis.zadd("zset", 1, "-a");
        System.out.println(jedis.zrange("zset", 0 , -1));
    }

    @Test
    public void testBitMap() {
        //测试 bitMap
        jedis.setbit("mybit", 1, "1");
        jedis.setbit("mybit1", 2, "1");
        System.out.println(jedis.getbit("mybit", 1));

        //做做 or and
        BitSet bit = new BitSet();
        bit.or(BitSet.valueOf(jedis.get("mybit".getBytes())));
        bit.or(BitSet.valueOf(jedis.get("mybit1".getBytes())));
        System.out.println(bit.cardinality());
    }

    @Test
    public void testExpire() throws InterruptedException {
        //设置过期时间
        jedis.setex("testExpire", 2, "expire???");
        System.out.println(jedis.get("testExpire"));
        Thread.sleep(2000);
        System.out.println(jedis.get("testExpire"));
    }

}
