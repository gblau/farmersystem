package com.gblau.service;

import com.gb.common.model.po.Town;
import com.gblau.service.base.BaseService;

import java.util.List;

/**
 * @author gblau
 * @date 2019-05-07
 */
public interface TownService extends BaseService<Town> {
    List<Town> fingByUserId(Integer userId);
}
