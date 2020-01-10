package com.jerry.springboot_redis_shiro.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 70023766637111203L;

    @TableId
    private Long userId;

    private String username;

    private String password;

    private String salt;

    private String state;
}
