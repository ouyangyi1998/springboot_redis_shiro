package com.jerry.springboot_redis_shiro.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jerry.springboot_redis_shiro.core.entity.SysUserEntity;

public interface SysUserService extends IService<SysUserEntity> {
    SysUserEntity selectUserByName(String username);
}
