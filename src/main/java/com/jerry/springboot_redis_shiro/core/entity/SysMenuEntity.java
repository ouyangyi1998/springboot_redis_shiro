package com.jerry.springboot_redis_shiro.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {

    private static final long serialVersionUID = -8141237270313529410L;

    @TableId
    private Long menuId;

    private String name;

    private String perms;
}
