package com.kad.rbac.model.entity.vo;

import com.kad.rbac.model.common.TreeVO;
import com.kad.rbac.model.entity.po.BaseDO;
import lombok.Data;

@Data
public class CompanyVO extends TreeVO {


    //公司id
    private Integer cmpId;
    //公司名称
    private String cmpName;
    //父级ID
    private Integer cmpPid;

    public Integer getCmpId()
    {
        return id;
    }

    public Integer getCmpPid()
    {
        return pid;
    }
}
