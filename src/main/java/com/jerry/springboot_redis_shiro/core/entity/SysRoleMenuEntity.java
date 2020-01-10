package com.jerry.springboot_redis_shiro.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {

    private static final long serialVersionUID = -1493640976648929968L;
    @TableId
    private Long id;

    private Long roleId;

    private Long menuId;
}
