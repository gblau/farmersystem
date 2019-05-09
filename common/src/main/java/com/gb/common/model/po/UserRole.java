package com.gb.common.model.po;

/**
 * @author gblau
 * @date 2019-05-01
 */
public class UserRole {
    String roleId;
    String userId;

    public String getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserRole setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
