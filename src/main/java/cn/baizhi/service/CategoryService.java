package cn.baizhi.service;

import cn.baizhi.entity.Category;

import java.util.List;

public interface CategoryService {

    //根据级别查询类别的业务
    List<Category> queryByLevels(int levels);

    //根据父项id查二级业务
    List<Category> queryByParentId(String id);

    //添加类别
    void save(Category category);

    //删除二级类别
    void delete(String id);
}
