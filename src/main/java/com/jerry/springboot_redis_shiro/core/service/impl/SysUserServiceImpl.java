package com.jerry.springboot_redis_shiro.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.springboot_redis_shiro.core.dao.SysUserDao;
import com.jerry.springboot_redis_shiro.core.entity.SysUserEntity;
import com.jerry.springboot_redis_shiro.core.service.SysUserService;
import org.springframework.stereotype.Service;

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Override
    public SysUserEntity selectUserByName(String username) {
        QueryWrapper<SysUserEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserEntity::getUsername,username);
        return this.baseMapper.selectOne(queryWrapper);
    }
}
