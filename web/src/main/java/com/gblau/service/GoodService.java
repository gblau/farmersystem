package com.gblau.service;

import com.gb.common.model.po.Good;
import com.gblau.service.base.BaseService;

import java.util.List;

/**
 * @author gblau
 * @date 2019-05-07
 */
public interface GoodService extends BaseService<Good> {
    List<Good> findByStore(Integer storeId);
}
