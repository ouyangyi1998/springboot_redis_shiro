package com.jerry.springboot_redis_shiro.controller;

import com.jerry.springboot_redis_shiro.commom.util.SHA256Util;
import com.jerry.springboot_redis_shiro.commom.util.ShiroUtils;
import com.jerry.springboot_redis_shiro.core.entity.SysUserEntity;
import com.jerry.springboot_redis_shiro.core.entity.SysUserRoleEntity;
import com.jerry.springboot_redis_shiro.core.service.SysUserRoleService;
import com.jerry.springboot_redis_shiro.core.service.SysUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/userLogin")
public class UserLoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody SysUserEntity sysUserEntity) {
        Map<String, Object> map = new HashMap<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUserEntity.getUsername(), sysUserEntity.getPassword());
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            map.put("code", 500);
            map.put("msg", "用户名不存在或者密码错误");
            return map;
        } catch (LockedAccountException e) {
            map.put("code", 500);
            map.put("msg", "登录失败，用户冻结");
            return map;
        } catch (AuthenticationException e) {
            map.put("code", 500);
            map.put("msg", "用户不存在");
            return map;
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "未知异常");
            return map;
        }
        map.put("code", 0);
        map.put("msg", "登陆成功");
        map.put("token", ShiroUtils.getSession().getId().toString());
        return map;
    }
    @RequestMapping("/unauth")
    public Map<String,Object> unauth()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("code",500);
        map.put("msg","未登录");
        return map;
    }
    @RequestMapping("/testAddUser")
    public Map<String,Object> testAddUser()
    {
        SysUserEntity sysUserEntity=new SysUserEntity();
        sysUserEntity.setUsername("user1");
        sysUserEntity.setState("NORMAL");
        String salt= RandomStringUtils.randomAlphanumeric(20);
        sysUserEntity.setSalt(salt);
        String password="123456";
        sysUserEntity.setPassword(SHA256Util.sha256(password,sysUserEntity.getSalt()));
        sysUserService.save(sysUserEntity);
        SysUserRoleEntity sysUserRoleEntity=new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(sysUserEntity.getUserId());
        sysUserRoleService.save(sysUserRoleEntity);
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","添加成功");
        return map;
    }
}
