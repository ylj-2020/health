package cn.ylj.mapper;

import cn.ylj.entity.Setmeal;

public interface SetmealMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Setmeal record);

    int insertSelective(Setmeal record);

    Setmeal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Setmeal record);

    int updateByPrimaryKey(Setmeal record);
}