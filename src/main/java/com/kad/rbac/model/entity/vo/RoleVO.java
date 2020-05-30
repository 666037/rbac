package com.kad.rbac.model.entity.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class RoleVO implements Serializable{
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色父id
     */
    private Integer rolePid;
    /**
     * 角色名称
     */
    private String roleName;

    //公司id
    private Integer cmpId;
    //公司名称
    private String cmpName;

    //组织机构id
    private Integer organiseId;

    //组织机构名称
    private String organiseName;

}
