package com.kad.rbac.model.entity.vo;

import com.kad.rbac.model.common.TreeVO;
import lombok.Data;

@Data
public class RightVO extends TreeVO {

    private String rightName;
    //标识组 多个以逗号隔开
    private String rightFlag;
    //图标
    private String rightIcon;
    //链接
    private String rightUrl;
    //类型（0：平台 1：模块 2：页面 3：API 4：组件）
    private Integer rightType;
    //级别
    private Integer rightLevel;
    private Integer rightSort;
    //是否禁用 0 正常 1 禁用
    private Integer rightDisabled;

      //平台类型
    private Integer rightPlatType;

    private  Integer rightId;
    private  Integer rightPid;
    public Integer getRightId()
    {
        return id;
    }
    public Integer getRightPid()
    {
        return pid;
    }
}
