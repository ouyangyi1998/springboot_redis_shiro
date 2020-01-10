package com.jerry.springboot_redis_shiro.controller;

import com.jerry.springboot_redis_shiro.commom.util.ShiroUtils;
import com.jerry.springboot_redis_shiro.core.entity.SysMenuEntity;
import com.jerry.springboot_redis_shiro.core.entity.SysRoleEntity;
import com.jerry.springboot_redis_shiro.core.entity.SysRoleMenuEntity;
import com.jerry.springboot_redis_shiro.core.entity.SysUserEntity;
import com.jerry.springboot_redis_shiro.core.service.SysMenuService;
import com.jerry.springboot_redis_shiro.core.service.SysRoleMenuService;
import com.jerry.springboot_redis_shiro.core.service.SysRoleService;
import com.jerry.springboot_redis_shiro.core.service.SysUserService;
import net.sf.saxon.expr.Component;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class UserMenuController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @RequestMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public Map<String,Object> getUserInfoList()
    {
        Map<String,Object> map=new HashMap<>();
        List<SysUserEntity> sysUserEntityList=sysUserService.list();
        map.put("sysUserEntityList",sysUserEntityList);
        return map;
    }
    @RequestMapping("/getRoleInfoList")
    @RequiresPermissions("sys:role:info")
    public Map<String,Object> getRoleInfoList()
    {
            Map<String,Object> map=new HashMap<>();
            List<SysRoleEntity> sysRoleEntityList=sysRoleService.list();
            map.put("sysRoleEntityList",sysRoleEntityList);
            return map;
    }
    @RequestMapping("getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public Map<String,Object> getMenuInfoList()
    {
        Map<String,Object> map=new HashMap<>();
        List<SysMenuEntity> sysMenuEntityList=sysMenuService.list();
        map.put("sysMenuEntityList",sysMenuEntityList);
        return map;
    }
    @RequestMapping("getInfoAll")
    @RequiresPermissions("sys:info:all")
    public Map<String,Object> getInfoAll()
    {
        Map<String,Object> map=new HashMap<>();
        List<SysUserEntity> sysUserEntityList=sysUserService.list();
        map.put("sysUserEntityList",sysUserEntityList);
        List<SysRoleEntity> sysRoleEntityList=sysRoleService.list();
        map.put("sysRoleEntityList",sysRoleEntityList);
        List<SysMenuEntity> sysMenuEntityList=sysMenuService.list();
        map.put("sysMenuEntityList",sysMenuEntityList);
        return map;
    }
    @RequestMapping("/addMenu")
    public Map<String,Object> addMenu()
    {
        SysRoleMenuEntity sysRoleMenuEntity=new SysRoleMenuEntity();
        sysRoleMenuEntity.setMenuId(4L);
        sysRoleMenuEntity.setRoleId(1L);
        sysRoleMenuService.save(sysRoleMenuEntity);
        String username="admin";
        ShiroUtils.deleteCache(username,false);
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","权限添加成功");
        return map;
    }
}
