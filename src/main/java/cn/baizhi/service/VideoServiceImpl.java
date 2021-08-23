package cn.baizhi.service;

import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao vd;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String,Object> map = new HashMap<>();

        //总页数
        int i = vd.querySize() / size;
        if (vd.querySize() % size != 0){
            i++;
        }
        map.put("count", i);
        //当前页数
        map.put("page", page);

        //分页查到的数据
        List<Video> list = vd.queryByPage((page - 1) * size, size);
        map.put("data", list);
        return map;
    }

    @DeleteCache
    @Override
    public void deleteVideo(String id) {
        vd.deleteVideo(id);
    }

    @DeleteCache
    @Override
    public void save(Video video) {
        video.setId(UUID.randomUUID().toString());
        vd.save(video);
    }
}
