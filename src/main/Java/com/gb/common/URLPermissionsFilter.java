package com.gb.common;

import com.gb.service.BlogUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by gblau on 2016-11-11.
 */
@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {
    @Autowired
    private BlogUserService userService;

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 获取当前URL+Parameter
     * @author lance
     * @since  2014年12月18日 下午3:09:26
     * @param request	拦截请求request
     * @return			返回完整URL
     */
    private String getRequestUrl(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest)request;
        String queryString = req.getQueryString();

        queryString = StringUtils.isBlank(queryString)?"": "?"+queryString;
        return req.getRequestURI()+queryString;
    }
}