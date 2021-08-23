package cn.baizhi.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class TestRedis {

    //操作对象
    @Autowired
    private RedisTemplate redisTemplate;
    //操作字符串
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void redisTest(){

        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("java", "123");
        System.out.println(stringStringValueOperations.get("java"));
    }
}
