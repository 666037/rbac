package com.kad.rbac.service;

import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.CompanyDO;
import com.kad.rbac.model.entity.vo.CompanyVO;


import java.util.List;

public interface CompanyService extends IBaseService<CompanyDO> {

    GenericCommonResult<List<CompanyVO>> GetCompanyList();
    public GenericCommonResult<List<CompanyVO>> GetTreeCompanyList();
    CommonResult AddList(List<CompanyDO> list);
}
