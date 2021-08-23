package cn.baizhi.dao;

import cn.baizhi.entity.Category;

import java.util.List;

public interface CategoryDao {
    //根据级别查询类型
    List<Category> queryByLevels(int levens);

    //根据父项id查询所有二级类别
    List<Category> queryByParendId(String id);

    //根据父项添加一二级级别
    void save(Category category);

    //根据id删除类别
    void delete(String id);
}
