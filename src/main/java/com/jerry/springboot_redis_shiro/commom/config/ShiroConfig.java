package com.jerry.springboot_redis_shiro.commom.config;

import com.jerry.springboot_redis_shiro.commom.shiro.ShiroRealm;
import com.jerry.springboot_redis_shiro.commom.shiro.ShiroSessionIdGenerator;
import com.jerry.springboot_redis_shiro.commom.shiro.ShiroSessionManager;
import com.jerry.springboot_redis_shiro.commom.util.SHA256Util;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private final String CACHE_KEY="shiro:cache:";
    private final String SESSION_KEY="shiro:session";
    private final int EXPIRE=1800;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/userLogin/**","anon");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/userLogin/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public SecurityManager securityManager()
    {
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }
    @Bean
    public ShiroRealm shiroRealm()
    {
        ShiroRealm shiroRealm=new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher()
    {
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }
    @Bean
    public RedisManager redisManager()
    {
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(timeout);
        return redisManager;
    }
    @Bean
    public RedisCacheManager redisCacheManager()
    {
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix(CACHE_KEY);
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }
    @Bean
    public ShiroSessionIdGenerator shiroSessionIdGenerator()
    {
        return new ShiroSessionIdGenerator();
    }

    @Bean
    public RedisSessionDAO redisSessionDAO()
    {
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(shiroSessionIdGenerator());
        redisSessionDAO.setKeyPrefix(SESSION_KEY);
        redisSessionDAO.setExpire(EXPIRE);
        return redisSessionDAO;
    }
    @Bean
    public SessionManager sessionManager()
    {
    ShiroSessionManager shiroSessionManager=new ShiroSessionManager();
    shiroSessionManager.setSessionDAO(redisSessionDAO());
    return shiroSessionManager;
    }
}
