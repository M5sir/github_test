package cn.baizhi.service;

import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;
import cn.baizhi.vo.MonthAndCount;
import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao ud;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String,Object> map = new HashMap<>();
        //map.put(, )
        //总页数
        int i = ud.querySize() / size;
        if (ud.querySize() % size != 0){
            i++;
        }
        map.put("count", i);
        //当前页数
        map.put("page", page);

        //分页查到的数据
        List<User> list = ud.queryRange((page - 1) * size, size);
        map.put("data", list);
        return map;
    }

    @DeleteCache
    @Override
    public void updateStatus(String id, int status) {
        ud.updateStatus(id, status);
    }

    @DeleteCache
    @Override
    public void add(User user) {
        user.setSex("男神");
        user.setId(UUID.randomUUID().toString());
        ud.add(user);
        Map<String,Object> map = quaryMan();
        Map<String,Object> map1= quaryWoman();
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-7de393bc1dbf4392b65bfa32fa038fce");
        goEasy.publish("my_channel", JSONObject.toJSONString(map));
    }


    @DeleteCache
    @Override
    public void deleteUser(String id) {
        ud.deleteUser(id);
//        Map<String,Object> map = quaryMan();
//        Map<String,Object> map1= quaryWoman();
//        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-7de393bc1dbf4392b65bfa32fa038fce");
//        goEasy.publish("my_channel", JSONObject.toJSONString(map));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> selectAll() {
        return ud.selectAll();
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> quaryMan() {
        List<MonthAndCount> monthAndCounts = ud.quaryMan();
        Map<String,Object> map = new HashMap<>();
        int[] aa = new int[12];
        for (int i = 1; i <= 12; i++) {
            for (MonthAndCount monthAndCount : monthAndCounts) {
                if (i==monthAndCount.getMonth()){
                    aa[i-1]=monthAndCount.getCount();
                    break;
                }else {
                    aa[i-1]=0;
                }
            }
        }
        List<int[]> manCount = Arrays.asList(aa);
        List<String> data = Arrays.asList("1月", "2月", "3月","4月", "5月", "6月","7月","8月","9月","10月","11月","12月");
        map.put("manCount", manCount);
        map.put("data", data);
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> quaryWoman() {
        List<MonthAndCount> monthAndCounts = ud.quaryWoman();
        Map<String,Object> map = new HashMap<>();
        int[] bb = new int[12];
        for (int i = 1; i <= 12; i++) {
            for (MonthAndCount monthAndCount : monthAndCounts) {
                if (i==monthAndCount.getMonth()){
                    bb[i-1]=monthAndCount.getCount();
                    break;
                }else {
                    bb[i-1]=0;
                }
            }
        }
        List<int[]> womanCount = Arrays.asList(bb);
        List<String> data = Arrays.asList("1月", "2月", "3月","4月", "5月", "6月","7月","8月","9月","10月","11月","12月");
        map.put("womanCount", womanCount);
        map.put("data", data);
        return map;
    }
}
