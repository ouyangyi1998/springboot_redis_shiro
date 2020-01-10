package com.jerry.springboot_redis_shiro.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.springboot_redis_shiro.core.dao.SysRoleDao;
import com.jerry.springboot_redis_shiro.core.entity.SysRoleEntity;
import com.jerry.springboot_redis_shiro.core.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }
}
