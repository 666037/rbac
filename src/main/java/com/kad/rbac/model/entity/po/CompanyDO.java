package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

@Data
@SqlAnnotationTableName(value = "rbac_company")
public class CompanyDO extends BaseDO{


    //公司id
    private Integer cmpId;
    //公司名称
    private String cmpName;
    //父级ID
    private Integer cmpPid;
}
