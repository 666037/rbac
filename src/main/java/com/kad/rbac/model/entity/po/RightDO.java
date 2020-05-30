package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

@Data
@SqlAnnotationTableName(value = "rbac_right")
public class RightDO extends BaseDO {


    //权限id
    private Integer rightId;
    //平台类型
    private Integer rightPlatType;
    //父级ID
    private Integer rightPid;
    //名称
    private String rightName;
    //标识组 多个以逗号隔开
    private String rightFlag;
    //图标
    private String rightIcon;
    //链接
    private String rightUrl;
    //类型（0：平台 1：模块 2：页面 3：API 4：组件）
    private Integer rightType;

    //是否禁用 0 正常 1 禁用
    private Integer rightDisabled;
    private Integer rightSort;
}
