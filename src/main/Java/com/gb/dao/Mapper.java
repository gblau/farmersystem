package com.gb.dao;

import java.util.List;

/**
 * Created by gblau on 2016-11-12.
 */
public interface Mapper<T> {
    int deleteByPrimaryKey(int id);
    int insert(T record);
    T selectByPrimaryKey(int id);
    List<T> selectAllElements();
    int updateByPrimaryKey(T record);
}
