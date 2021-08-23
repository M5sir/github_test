package cn.baizhi.test;

import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao ud;

    @Test
    public void testQuery(){
        List<User> users = ud.queryRange(0, 3);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFile(){
        String s="http://tongt123456.oss-cn-beijing.aliyuncs.com/";
        String s1 = "http://tongt123456.oss-cn-beijing.aliyuncs.com/video/";
        System.out.println(s.length());
        System.out.println(s1.length());
    }
}
