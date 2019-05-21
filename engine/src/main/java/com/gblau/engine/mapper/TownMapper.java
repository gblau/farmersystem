package com.gblau.engine.mapper;

import com.gb.common.model.po.Town;

import java.util.List;

public interface TownMapper extends Mapper<Town> {

    List<Town> selectByUserId(Integer id);
}