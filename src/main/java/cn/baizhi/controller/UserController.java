package cn.baizhi.controller;

import cn.baizhi.entity.User;
import cn.baizhi.service.UserService;
import cn.baizhi.util.DeleteFile;
import cn.baizhi.util.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService us;

    @RequestMapping("/queryByPage")
    public Map<String, Object> queryByPage(int page){
        int size = 3;

        Map<String, Object> map = us.queryByPage(page, size);
        return map;
    }

    @RequestMapping("/updateStatus")
    public void updateStatus(String id,int status){
        log.debug(id+"       "+status);
        us.updateStatus(id, status);
    }

    @RequestMapping("/add")
    public void add(MultipartFile photo,String username,String phone,String brief){
        us.add(new User(null, username, phone, "http://tongt123456.oss-cn-beijing.aliyuncs.com/"+photo.getOriginalFilename(), brief, null, new Date(), 0,null));
        File.uploadAliyun(photo);
    }

    @RequestMapping("/delete")
    public void delete(String id,String photo){
        //System.out.println(id);
        //System.out.println(photo);
        String s = photo.substring(47);
        DeleteFile.deleteFile(s);
        us.deleteUser(id);
       // DeleteFile.deleteFile();
    }

    @RequestMapping("/registManCount")
    public Map<String, Object> registManCount(){
        return us.quaryMan();
        /*//System.out.println("执行了");
        //Arrays集合工具类
        List<String> data = Arrays.asList("1月", "2月", "3月","4月", "5月", "6月","7月","8月","9月","10月","11月","12月");
        List<Integer> manCount = Arrays.asList(5, 20, 36, 10, 10, 20);
        List<Integer> womanCount = Arrays.asList(15, 20, 34, 10, 40, 3);

        //存储了月份  男女神注册人数
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        map.put("manCount", manCount);
        map.put("womanCount", womanCount);
*/
    }
    @RequestMapping("/registWomanCount")
    public Map<String,Object> registWomanCount(){
        Map<String, Object> map = us.quaryWoman();
        return map;
    }

}
