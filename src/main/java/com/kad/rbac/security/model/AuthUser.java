package com.kad.rbac.security.model;

import lombok.Data;

import java.util.List;

/**
 * 当前登录的用户信息
 */
@Data
public class AuthUser {

    /**
     * 用户名
     */
    private String UserName;
    /**
     * 真实姓名
     */
    private  String RealName;
    /**
     * 角色id
     */
    private  int RoleId;
    /**
     * 角色名称
     */
    private  String RoleName;
    /**
     * 权限集合
     */
    private List<Right>Rights;
}
