package cn.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Set;

//@Aspect
//@Component
public class CacheAspect {
    @Autowired
    private RedisTemplate redisTemplate;
/*
* execution():方法级别
* within():类级别
* @annotation():注解方式
* */
    @Around("execution(* cn.baizhi.service.*Impl.*(..))")
    public Object addCache(ProceedingJoinPoint joinPoint){
        System.out.println("进入环绕通知");

        StringBuilder sb = new StringBuilder();//StringBuilder做拼接不会浪费内存资源


        //获取类的全路径
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println("类的全路径====" + className);
        //获取方法名
        String methondName = joinPoint.getSignature().getName();
        System.out.println("类的方法名====" + methondName);

        sb.append(className).append(methondName);

        //获取实参
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("实参的值====" + arg);
            sb.append(arg);
        }

        /*
        * 判断redis中已经缓存了数据，就不需要放行，直接从redis中取
        * */

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        ValueOperations valueOperations = redisTemplate.opsForValue();

        Object obj = null;

        if (redisTemplate.hasKey(sb.toString())){

            //如果key存在

            obj = valueOperations.get(sb.toString());


        }else {
            //没有这个key
            //Object obj = null;//放行请求
            try {
                obj = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            valueOperations.set(sb.toString(), obj);
        }


        //System.out.println(obj);
        /*
        * key  是类名全路径  +  方法名  +  实参
        * */



        //System.out.println(sb);
        return obj;//数据到达controller
    }
    /*
     * 只要执行了增删改，就应该清除缓存
     * 开发一个删除缓存的功能
     * 使用后置通知
     * */
    @After("@annotation(cn.baizhi.annotation.DeleteCache)")
    public void delCache(JoinPoint joinPoint){
        System.out.println("后置通知");
        /*
        * 进入后置通知应该清楚缓存
        * */
        String ClassName = joinPoint.getTarget().getClass().getName();
        System.out.println(ClassName);

        Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            String newkey = (String)key;
            if (newkey.startsWith(ClassName)){
                redisTemplate.delete(key);
            }
        }

        /*
        *
        * */
    }

}
