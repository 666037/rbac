package com.kad.rbac.service;

import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.common.PageCommonResult;
import com.kad.rbac.model.entity.vo.QueryRoleVO;
import com.kad.rbac.model.entity.vo.RoleVO;

import java.util.List;

public interface RoleService  {


    PageCommonResult<RoleVO> GetRoleList(QueryRoleVO query);

    Integer GetRoleListCount(QueryRoleVO query);

    GenericCommonResult<List<Integer>> getRoleRightIdList(String roleId);
}
