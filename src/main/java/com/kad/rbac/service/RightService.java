package com.kad.rbac.service;

import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.vo.RoleRightVO;
import com.kad.rbac.model.entity.vo.RightVO;

import java.util.List;

public interface RightService extends IBaseService<RightDO>{



    public GenericCommonResult<List<RightVO>> GetMenuList(List<Integer>roles);
    public GenericCommonResult<List<RightVO>> GetTreeMenuList(List<Integer>roles);
    public CommonResult AddList(List<RightDO>list);
    public CommonResult AddRoleRight(RoleRightVO roleRightVO);
    public CommonResult UpdateRoleRight(RoleRightVO roleRightVO);
    GenericCommonResult<List<RightVO>> GetRightList();
}
