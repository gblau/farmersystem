package com.gb.common.model.po;

/**
 * @author gblau
 * @date 2019-05-01
 */
public class UserRole {
    Integer roleId;
    Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserRole setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
