package cn.baizhi.dao;

import cn.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {

    //查所有的方法
    List<Video> queryByPage(@Param("start") int start, @Param("end") int end);

    //总条数
    int querySize();

    //删除  根据id删一个
    void deleteVideo(String id);

    //添加
    void save(Video video);
}
