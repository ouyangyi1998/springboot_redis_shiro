package com.jerry.springboot_redis_shiro.commom.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class ShiroSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION="Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE="Stateless request";
    public ShiroSessionManager()
    {
        super();
        this.setDeleteInvalidSessions(true);
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token= WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if(!StringUtils.isEmpty(token))
        {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }else
        {
            return null;
        }
    }
}
