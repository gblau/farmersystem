package com.gblau.service.base;

import java.util.List;

/**
 * Created by gblau on 2016-11-12.
 */
public interface BaseService<T> {
    void setBaseMapper();

    int deleteByPrimaryKey(Integer id);
    int insert(T record);
    int insertSelective(T record);
    T findByPrimaryKey(Integer id);
    List<T> findAllElements();
    int updateByPrimaryKey(T record);
    int updateByPrimaryKeySelective(T record);
}