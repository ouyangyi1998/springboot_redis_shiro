package com.jerry.springboot_redis_shiro.commom.util;

import com.jerry.springboot_redis_shiro.core.entity.SysUserEntity;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.apache.shiro.authc.Authenticator;
import java.util.Collection;
import java.util.Objects;

public class ShiroUtils {
    private ShiroUtils(){}

    private static RedisSessionDAO redisSessionDAO=SpringUtil.getBean(RedisSessionDAO.class);

    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }
    public static void logout()
    {
        SecurityUtils.getSubject().logout();
    }
    public static SysUserEntity getUserInfo()
    {
        return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
    }
    public static void deleteCache(String username,boolean isRemoveSession)
    {
        Session session=null;
        Collection<Session> sessions=redisSessionDAO.getActiveSessions();
        SysUserEntity sysUserEntity;
        Object attribute=null;
        for (Session sessionInfo:sessions)
        {
            attribute=sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(attribute==null)
            {
                continue;
            }
            sysUserEntity=(SysUserEntity)((SimplePrincipalCollection)attribute).getPrimaryPrincipal();
            if(sysUserEntity==null)
            {
                continue;
            }
            if(Objects.equals(sysUserEntity.getUsername(),username)){
                session=sessionInfo;
                break;
            }
        }
        if(isRemoveSession)
        {
            redisSessionDAO.delete(session);
        }
        DefaultWebSecurityManager securityManager=(DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        Authenticator authc=securityManager.getAuthenticator();
        ((LogoutAware)authc).onLogout((SimplePrincipalCollection)attribute);
    }

}
