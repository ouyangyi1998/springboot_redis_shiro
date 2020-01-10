package com.jerry.springboot_redis_shiro.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jerry.springboot_redis_shiro.core.entity.SysMenuEntity;

import java.util.List;

public interface SysMenuService extends IService<SysMenuEntity> {
    List<SysMenuEntity> selectSysMenuRoleId(Long roleId);
}
