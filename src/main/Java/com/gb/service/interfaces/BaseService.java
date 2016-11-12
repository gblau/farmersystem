package com.gb.service.interfaces;

import com.gb.dao.DataAccessObject;

/**
 * Created by gblau on 2016-11-12.
 */
public interface BaseService<T> {
    void setBaseMapper(DataAccessObject dao);
    int deleteByPrimaryKey(int id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(int id);
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKeyWithBLOBs(T record);
    int updateByPrimaryKey(T record);
}