package com.jerry.springboot_redis_shiro.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.springboot_redis_shiro.core.entity.SysMenuEntity;

import java.util.List;

public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);
}
