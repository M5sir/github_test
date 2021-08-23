package cn.baizhi.dao;

import cn.baizhi.entity.Admin;

public interface AdminDao {
    //根据用户名查一个
    Admin queryByUser(String username);
}
