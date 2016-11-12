package com.gb.dao;

import com.gb.model.User;

import java.util.List;

/**
 * Created by gblau on 2016-11-12.
 */
public interface UserDataAccessObject extends DataAccessObject<User> {
    /**
     * 通过父id得到类目列表
     * @param parent
     * @return
     */
    List<User> selectByParent(int parent);
}
