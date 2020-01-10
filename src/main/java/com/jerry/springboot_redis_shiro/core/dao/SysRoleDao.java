package com.jerry.springboot_redis_shiro.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.springboot_redis_shiro.core.entity.SysRoleEntity;

import java.util.List;

public interface SysRoleDao extends BaseMapper<SysRoleEntity>
{
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
}
