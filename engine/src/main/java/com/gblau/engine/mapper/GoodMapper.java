package com.gblau.engine.mapper;

import com.gb.common.model.po.Good;

import java.util.List;

public interface GoodMapper extends Mapper<Good> {
    List<Good> selectByStore(Integer storeId);
}