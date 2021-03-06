package cn.ylj.service;

import cn.ylj.entity.Checkgroup;
import cn.ylj.model.QueryPageBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICheckgroupService {

    int add(Checkgroup checkgroup, Integer[] checkitemIds);

    PageInfo<Checkgroup> findPage(QueryPageBean pageBean);

    void deleteById(Integer id);

    Checkgroup findById(Integer id);

    void update(Checkgroup checkgroup, Integer[] ids);

    List<Checkgroup> findAll();
}
