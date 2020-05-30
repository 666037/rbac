package com.kad.rbac.service;

import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.OrganiseDO;
import com.kad.rbac.model.entity.po.OrganiseRoleDO;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.vo.OrganiseVO;
import com.kad.rbac.model.entity.vo.QueryOrganiseVO;
import com.kad.rbac.model.entity.vo.RightVO;

import java.util.List;

public interface OrganiseService extends IBaseService<OrganiseDO>{


    public GenericCommonResult<List<OrganiseVO>> GetOrganiseList(QueryOrganiseVO vo);
    public GenericCommonResult<List<OrganiseVO>> GetTreeOrganiseList(QueryOrganiseVO vo);
    public CommonResult AddList(List<OrganiseDO>list);
    GenericCommonResult<List<OrganiseRoleDO>>  getOrganiseRoleListByOrganiseId(Integer organiseId);

}
