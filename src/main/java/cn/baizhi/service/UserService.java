package cn.baizhi.service;

import cn.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    //分页查业务
    Map<String,Object> queryByPage(int page,int size);

    //修改状态的业务
    void updateStatus(String id,int status);

    //添加用户
    void add(User user);

    //删除用户
    void deleteUser(String id);

    //查所有
    List<User> selectAll();

    //查询注册男神人数
    Map<String,Object> quaryMan();

    //查询注册女神人数
    Map<String,Object> quaryWoman();

}
