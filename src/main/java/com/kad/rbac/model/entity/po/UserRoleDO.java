package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

@Data
@SqlAnnotationTableName(value = "rbac.rbac_user_role")
public class UserRoleDO {

    private  Integer id;
    private  String  username;
    private  Integer roleId;
}
