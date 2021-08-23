package cn.baizhi.controller;


import cn.baizhi.entity.Category;
import cn.baizhi.entity.Video;
import cn.baizhi.service.VideoService;
import cn.baizhi.util.DeleteFile;
import cn.baizhi.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService vs;

    @RequestMapping("/queryByPage")
    public Map<String, Object> queryByPage(int page){
        int size = 1;


        Map<String,Object> map = vs.queryByPage(page, size);
        return map;
    }

    @RequestMapping("/deleteVideo")
    public void deleteVideo(String id,String videoPath){
        System.out.println(id);
        System.out.println(videoPath);
        String s = videoPath.substring(53);
        DeleteFile.deleteVideo(s);
        vs.deleteVideo(id);
    }
    @RequestMapping("/save")
    public void save(MultipartFile video,String title,String brief,String id){
        System.out.println("执行了");
        System.out.println(video.getOriginalFilename());
        vs.save(new Video(null, title, brief, null, "https://tongt123456.oss-cn-beijing.aliyuncs.com/video/"+video.getOriginalFilename(), new Date(), new Category(id), null, null));
        File.saveVideo(video);
    }
}
