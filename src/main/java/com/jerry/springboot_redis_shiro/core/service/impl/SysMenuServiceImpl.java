package com.jerry.springboot_redis_shiro.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.springboot_redis_shiro.core.dao.SysMenuDao;
import com.jerry.springboot_redis_shiro.core.entity.SysMenuEntity;
import com.jerry.springboot_redis_shiro.core.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity>implements SysMenuService {
    @Override
    public List<SysMenuEntity> selectSysMenuRoleId(Long roleId) {
        return this.baseMapper.selectSysMenuByRoleId(roleId);
    }
}
