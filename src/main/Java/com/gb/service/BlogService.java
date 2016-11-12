package com.gb.service;

import com.gb.dao.DataAccessObject;
import com.gb.service.interfaces.BaseService;

/**
 * Created by gblau on 2016-11-12.
 */
public abstract class BlogService<T> implements BaseService<T> {
    private DataAccessObject<T> dao;

    @Override
    public void setBaseMapper(DataAccessObject dao) {
        this.dao = dao;
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
    public int insertSelective(T record) {
        return dao.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(int id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(T record) {
        return dao.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return dao.updateByPrimaryKey(record);
    }
}
