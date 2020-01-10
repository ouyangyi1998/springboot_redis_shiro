package com.jerry.springboot_redis_shiro.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {

    private static final long serialVersionUID = -1017045944074333463L;

    @TableId
    private Long id;

    private Long userId;

    private Long roleId;
}
