package cn.ylj.service;

import cn.ylj.entity.Checkitem;
import cn.ylj.model.PageResult;
import cn.ylj.model.QueryPageBean;

public interface ICheckItemService {

    void add(Checkitem checkitem);

    PageResult pageQuery(QueryPageBean pb);
}
