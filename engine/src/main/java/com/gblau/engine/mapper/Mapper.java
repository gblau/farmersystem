package com.gblau.engine.mapper;

import java.util.List;

/**
 * Created by gblau on 2016-11-12.
 */
public interface Mapper<T> {
    int deleteByPrimaryKey(Integer id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(Integer id);
    List<T> selectAll();
    int updateByPrimaryKey(T record);
    int updateByPrimaryKeySelective(T record);
}
