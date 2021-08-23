package cn.baizhi.service;

import java.util.Map;

public interface AdminService {
    //登录业务    根据名字查一个
    Map<String,Object> login (String username, String password);
}
