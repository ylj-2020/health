package cn.ylj.mapper;

import cn.ylj.entity.Checkitem;

public interface CheckitemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Checkitem record);

    int insertSelective(Checkitem record);

    Checkitem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Checkitem record);

    int updateByPrimaryKey(Checkitem record);
}