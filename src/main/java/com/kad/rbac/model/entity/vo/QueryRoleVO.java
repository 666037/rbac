package com.kad.rbac.model.entity.vo;

import com.kad.rbac.model.common.PageQuery;
import lombok.Data;

@Data
public class QueryRoleVO extends PageQuery {

    private String cmpId;

    private String organiseId;

    private String roleName;

    private  String roleId;
}
