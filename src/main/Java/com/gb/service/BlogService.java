package com.gb.service;

import com.gb.dao.DataAccessObject;
import com.gb.service.interfaces.BaseService;

import java.util.List;

/**
 * Created by gblau on 2016-11-12.
 */
public abstract class BlogService<T> implements BaseService<T> {
    private DataAccessObject<T> dao;

    public void setDataAccessObject(DataAccessObject<T> dao) {
        this.dao = dao;
    }

    @Override
    public T findByPrimaryKey(int id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<T> findAllElements() {
        return dao.selectAllElements();
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return dao.insert(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return dao.updateByPrimaryKey(record);
    }
}
