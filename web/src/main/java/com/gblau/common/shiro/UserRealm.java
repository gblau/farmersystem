package com.gblau.common.shiro;

import com.gb.common.model.po.User;
import com.gb.common.util.Log;
import com.gblau.service.authorization.UserService;
import com.gblau.shiro.realm.DefaultUserRealm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gblau
 * @date 2017-09-10
 */
public class UserRealm extends DefaultUserRealm {
    @Autowired
    private UserService userService;

    /**
     * 通过userId，设置该用户的角色和所拥有的权限
     *
     * @param authorizationInfo
     * @param username
     */
    @Override
    protected void setUserRolesAndPermissions(SimpleAuthorizationInfo authorizationInfo, String username) {
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = userService.findUserByUsername((String) token.getPrincipal());
        if (user == null)
            return null;

        // 获取盐值，即用户名
        Log.error("盐：{}", token.getPrincipal());
        ByteSource salt = ByteSource.Util.bytes(token.getPrincipal());
        // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), salt, getName());
    }

    /**
     * 判断是否存在该用户。
     *
     * @param username
     * @return boolean 存在与否
     */
    @Override
    protected boolean isUserExist(String username) {
        return userService.findUserByUsername(username) == null;
    }

}
