package com.jerry.springboot_redis_shiro.commom.shiro;

import com.jerry.springboot_redis_shiro.commom.constant.RedisConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

public class ShiroSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId=new JavaUuidSessionIdGenerator().generateId(session);
        return String.format(RedisConstant.REDIS_PREFIX_LOGIN,sessionId);
    }
}
