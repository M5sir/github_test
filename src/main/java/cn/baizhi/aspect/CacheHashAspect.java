package cn.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheHashAspect {
    @Autowired
    private RedisTemplate redisTemplate;

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
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        HashOperations hashOperations = redisTemplate.opsForHash();

        Object obj = null;

        if (hashOperations.hasKey(className, sb.toString())){

            //如果key存在

            obj = hashOperations.get(className, sb.toString());


        }else {
            //没有这个key
            //Object obj = null;//放行请求
            try {
                obj = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            hashOperations.put(className, sb.toString(), obj);
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
       redisTemplate.delete(ClassName);
    }

}
