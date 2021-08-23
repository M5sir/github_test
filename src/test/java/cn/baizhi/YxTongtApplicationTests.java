package cn.baizhi;

import cn.baizhi.dao.AdminDao;
import cn.baizhi.entity.Admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class YxTongtApplicationTests {

    @Autowired
    private AdminDao adminDao;


    @Test
    public void contextLoads(){
        Admin admin = adminDao.queryByUser("java");
        System.out.println(admin);
    }

    }


