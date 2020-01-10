package com.jerry.springboot_redis_shiro.commom.shiro;

import com.jerry.springboot_redis_shiro.commom.util.ShiroUtils;
import com.jerry.springboot_redis_shiro.core.entity.SysMenuEntity;
import com.jerry.springboot_redis_shiro.core.entity.SysRoleEntity;
import com.jerry.springboot_redis_shiro.core.entity.SysUserEntity;
import com.jerry.springboot_redis_shiro.core.service.SysMenuService;
import com.jerry.springboot_redis_shiro.core.service.SysRoleService;
import com.jerry.springboot_redis_shiro.core.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        SysUserEntity sysUserEntity=(SysUserEntity)principalCollection.getPrimaryPrincipal();
        Long userId=sysUserEntity.getUserId();
        Set<String> roleSet=new HashSet<>();
        Set<String> permsSet=new HashSet<>();
        List<SysRoleEntity> sysRoleEntityList=sysRoleService.selectSysRoleByUserId(userId);
        for(SysRoleEntity sysRoleEntity:sysRoleEntityList){
            roleSet.add(sysRoleEntity.getRoleName());
            List<SysMenuEntity> sysMenuEntityList=sysMenuService.selectSysMenuRoleId(sysRoleEntity.getRoleId());
            for (SysMenuEntity sysMenuEntity:sysMenuEntityList)
            {
                permsSet.add(sysMenuEntity.getPerms());
            }
        }
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(roleSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String)authenticationToken.getPrincipal();
        SysUserEntity user=sysUserService.selectUserByName(username);
        if (user==null)
        {
            throw new AuthenticationException();
        }
        if(user.getState()==null||user.getState().equals("PROHIBIT"))
        {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authorizationInfo=new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName());
        ShiroUtils.deleteCache(username,true);
        return authorizationInfo;
    }
}
