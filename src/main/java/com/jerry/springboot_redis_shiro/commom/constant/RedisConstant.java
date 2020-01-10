package com.jerry.springboot_redis_shiro.commom.constant;

public interface RedisConstant {

    String REDIS_PREFIX_LOGIN="login_token_%s";

    Integer REDIS_EXPIRE_TWO=7200;

    Integer REDIS_EXPIRE_EMAIL=900;

    Integer REDIS_EXPIRE_KAPTCHA=300;

    Integer REDIS_EXPIRE_NULL=-1;
}
