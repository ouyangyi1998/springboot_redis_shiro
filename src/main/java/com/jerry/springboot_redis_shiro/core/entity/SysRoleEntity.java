package com.jerry.springboot_redis_shiro.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = -1134121648024265297L;

    @TableId
    private Long roleId;

    private String roleName;
}
