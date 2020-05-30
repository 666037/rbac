package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

@Data
@SqlAnnotationTableName(value = "rbac.rbac_role_right")
public class RoleRightDO extends BaseDO{

    private Integer roleId;
    private Integer rightId;

}
