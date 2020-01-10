package com.jerry.springboot_redis_shiro.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jerry.springboot_redis_shiro.core.entity.SysRoleEntity;

import java.util.List;

public interface SysRoleService extends IService<SysRoleEntity> {
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
}
