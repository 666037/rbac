package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

@Data
@SqlAnnotationTableName(value = "rbac.rbac_organise_role")
public class OrganiseRoleDO {

    private  Integer id;
    private Integer roleId;
    private Integer organiseId;
}
