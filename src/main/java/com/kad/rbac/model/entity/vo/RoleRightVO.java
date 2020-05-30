package com.kad.rbac.model.entity.vo;

import lombok.Data;

import java.util.List;
@Data
public class RoleRightVO {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;
   //部门id
    private Integer organiseId;
    ///公司id
    private String cmpId;
    //权限集合
    private List<Integer> rightIds;
///序号
    private String   roleSort;
}
