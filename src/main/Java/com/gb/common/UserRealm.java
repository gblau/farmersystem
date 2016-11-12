package com.gb.common;

import com.gb.service.BlogUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gblau on 2016-11-11.
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private BlogUserService userService;

    @Override
    public String getName() {
        return "userRealm";
    }

    //支持UsernamePasswordToken
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 用户验证完成后，在这个方法里给用户添加相应的权限。
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        int id = Integer.parseInt((String) principals.getPrimaryPrincipal());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(id));
        authorizationInfo.setStringPermissions(userService.findPermissions(id));
        return authorizationInfo;
    }

    /**
     * 认证一个用户Token是否正确
     * @param token the authentication token containing the user's principal and credentials.
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中 获取用户身份信息
        String username = (String) token.getPrincipal();
        //拿username从数据库中查询
        //....
        //如果查询不到则返回null
        if(!username.equals("zhang")){//这里模拟查询不到
            return null;
        }

        //获取从数据库查询出来的用户密码
        String password = "123";//这里使用静态数据模拟。。

        //返回认证信息由父类AuthenticatingRealm进行认证
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                username, password, getName());

        return simpleAuthenticationInfo;
    }
}
