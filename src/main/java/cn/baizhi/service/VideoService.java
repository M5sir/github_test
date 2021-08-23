package cn.baizhi.service;

import cn.baizhi.entity.Video;

import java.util.Map;

public interface VideoService {

    //分页查
    Map<String,Object> queryByPage(int page,int size);

    //删除
    void deleteVideo(String id);

    //添加
    void save(Video video);

}
