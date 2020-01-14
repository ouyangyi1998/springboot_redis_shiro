package com.jerry.springboot_redis_shiro.commom.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class SHA256Util {
    private SHA256Util(){}
    public final static String HASH_ALGORITHM_NAME="SHA-256";
    public final static int HASH_ITERATIONS=5;
    public static String sha256(String password,String salt)
    {
        return new SimpleHash(HASH_ALGORITHM_NAME,password,salt,HASH_ITERATIONS).toString();
    }
}
