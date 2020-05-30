package com.kad.rbac.model.entity.vo;

import com.kad.rbac.model.common.TreeVO;
import com.kad.rbac.model.entity.po.BaseDO;
import lombok.Data;

@Data
public class OrganiseVO extends TreeVO {



    //组织机构id
    private Integer organiseId;
    //组织机构父id，一级为0
    private Integer organisePid;
    //组织机构名称
    private String organiseName;

    //层次
    private Integer organiseLevel;
    //负责人名字
    private String organiseAdminName;
    //负责人用户名
    private String organiseAdminUserame;
    //排序
    private  Integer organiseSort;
    private  Integer organiseDisabled;
    private  Integer cmpId;
    private String cmpName;
    public Integer getOrganiseId()
    {
        return id;
    }

    public Integer getOrganisePid()
    {
        return pid;
    }
}
