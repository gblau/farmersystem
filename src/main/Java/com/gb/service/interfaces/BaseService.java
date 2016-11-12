package com.gb.service.interfaces;

import java.util.List;

/**
 * Created by gblau on 2016-11-12.
 */
public interface BaseService<T> {
    void setBaseMapper();

    int deleteByPrimaryKey(int id);
    int insert(T record);
    T findByPrimaryKey(int id);
    List<T> findAllElements();
    int updateByPrimaryKey(T record);
}