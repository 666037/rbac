package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

@Data
@SqlAnnotationTableName(value = "rbac.rbac_role")
public class RoleDO extends BaseDO {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

}
