package com.gb.dao;

/**
 * Created by gblau on 2016-11-12.
 */
public interface DataAccessObject<T> {
    int deleteByPrimaryKey(int id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(int id);
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKeyWithBLOBs(T record);
    int updateByPrimaryKey(T record);
}
