package com.jerry.springboot_redis_shiro.controller;

import com.jerry.springboot_redis_shiro.commom.util.ShiroUtils;
import com.jerry.springboot_redis_shiro.core.service.SysMenuService;
import com.jerry.springboot_redis_shiro.core.service.SysRoleMenuService;
import com.jerry.springboot_redis_shiro.core.service.SysRoleService;
import com.jerry.springboot_redis_shiro.core.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class UserRoleController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @RequestMapping("/getAdminInfo")
    @RequiresRoles("ADMIN")
    public Map<String,Object> getAdminInfo()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","这里是只有管理员能访问的接口");
        return map;
    }
    @RequestMapping("/getUserInfo")
    @RequiresRoles("USER")
    public Map<String,Object> getUserInfo()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","这里是只有用户角色能访问的接口");
        return map;
    }
    @RequestMapping("/getRoleInfo")
    @RequiresRoles(value = {"ADMIN","USER"},logical = Logical.OR)
    @RequiresUser
    public Map<String,Object> getRoleInfo()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","这里是只有ADMIN或者USER角色能访问的接口");
        return map;
    }
    public Map<String,Object> getLogout()
    {
        ShiroUtils.logout();
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","登出");
        return map;
    }
}
