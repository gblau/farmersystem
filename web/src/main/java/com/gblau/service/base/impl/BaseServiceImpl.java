package com.gblau.service.base.impl;

import com.gblau.service.base.BaseService;
import com.gblau.engine.mapper.Mapper;

import java.util.List;

/**
 * Created by gblau on 2016-11-12.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    private Mapper<T> dao;

    protected void setDataAccessObject(Mapper<T> dao) {
        this.dao = dao;
    }

    @Override
    public T findByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<T> findAllElements() {
        return dao.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
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
    public int updateByPrimaryKey(T record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return dao.updateByPrimaryKeySelective(record);
    }


}
