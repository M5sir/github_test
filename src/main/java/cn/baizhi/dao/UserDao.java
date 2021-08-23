package cn.baizhi.dao;

import cn.baizhi.entity.User;
import cn.baizhi.vo.MonthAndCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //范围查询  返回多条
    List<User> queryRange(@Param("start") int start, @Param("end") int end);
    //总条数
    int querySize();

    //变更状态
    void updateStatus (@Param("id") String id,@Param("status") int status);

    //添加用户
    void add(User user);

    //删除用户
    void deleteUser(String id);

    //查所有
    List<User> selectAll();

    //查询注册男神人数
    List<MonthAndCount> quaryMan();

    //查询注册女神人数
    List<MonthAndCount> quaryWoman();

}

