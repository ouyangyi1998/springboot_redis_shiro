package com.jerry.springboot_redis_shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.jerry.springboot_redis_shiro.core.dao"})
public class SpringbootRedisShiroApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootRedisShiroApplication.class, args);
    }

}
