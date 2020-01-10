package com.jerry.springboot_redis_shiro.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.springboot_redis_shiro.core.dao.SysRoleMenuDao;
import com.jerry.springboot_redis_shiro.core.entity.SysRoleMenuEntity;
import com.jerry.springboot_redis_shiro.core.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {
}
