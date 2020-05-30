package com.kad.rbac.service.impl;

import com.kad.rbac.dal.CompanyDao;
import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.CompanyDO;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.vo.CompanyVO;
import com.kad.rbac.model.entity.vo.RightVO;
import com.kad.rbac.util.TreeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService implements com.kad.rbac.service.CompanyService {

    @Autowired
    CompanyDao companyDao;

    @Override
    public int AddTargetList(List<CompanyDO> list) {
        return companyDao.AddCompanyList(list);
    }

    @Override
    public GenericCommonResult<List<CompanyVO>> GetCompanyList() {
        List<CompanyVO>res=companyDao.GetCompanyList();
        return GenericCommonResult.build(res,"获取数据成功",true,0);
    }

    @Override
    public GenericCommonResult<List<CompanyVO>> GetTreeCompanyList() {
        List<CompanyVO>res=companyDao.GetCompanyList();
        return GenericCommonResult.build((List<CompanyVO>)TreeHelper.ConvertToTree(res),"获取数据成功",true,0);
    }

    @Override
    public CommonResult AddList(List<CompanyDO> list) {
        int count= AddTargetList(list);
        return CommonResult.build(null,"保存成功",count>0,0);
    }
}
