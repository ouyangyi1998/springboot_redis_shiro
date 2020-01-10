package com.jerry.springboot_redis_shiro.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.springboot_redis_shiro.core.dao.SysUserRoleDao;
import com.jerry.springboot_redis_shiro.core.entity.SysUserRoleEntity;
import com.jerry.springboot_redis_shiro.core.service.SysUserRoleService;
import org.springframework.stereotype.Service;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {
}
