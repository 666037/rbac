package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

@Data
@SqlAnnotationTableName(value = "rbac_organise")
public class OrganiseDO extends BaseDO {

    //组织机构id
    private Integer organiseId;
    //组织机构父id，一级为0
    private Integer organisePid;
    //组织机构名称
    private String organiseName;

    //负责人名字
    private String organiseAdminName;
    //负责人用户名
    private String organiseAdminUsername;
    //排序
    private  Integer organiseSort;
    private  Integer organiseDisabled=0;
   //公司代码
    private  Integer cmpId;
}
