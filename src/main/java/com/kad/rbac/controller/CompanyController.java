package com.kad.rbac.controller;


import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.CompanyDO;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.vo.CompanyVO;
import com.kad.rbac.model.entity.vo.QueryOrganiseVO;
import com.kad.rbac.model.entity.vo.RightVO;
import com.kad.rbac.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @RequestMapping(value = "/company/getTreeCompanyList")
    public GenericCommonResult<List<CompanyVO>> getTreeCompanyList(QueryOrganiseVO vo) {

        GenericCommonResult<List<CompanyVO>> res=companyService.GetTreeCompanyList();
        return res;

    }
    @RequestMapping(value = "/company/getCompanyList")
    public GenericCommonResult<List<CompanyVO>> getCompanyList() {

        GenericCommonResult<List<CompanyVO>> res=companyService.GetCompanyList();
        return res;

    }
    @RequestMapping(value = "/company/addCompany",method = RequestMethod.POST)
    public CommonResult addCompany(@RequestBody CompanyDO company) {

        return companyService.AddList(new ArrayList<CompanyDO>() {
            {
                add(company);
            }
        });

    }
    @RequestMapping(value = "/company/updateCompany",method = RequestMethod.POST)
    public CommonResult updateCompany(@RequestBody CompanyDO company) {

        return companyService.AddList(new ArrayList<CompanyDO>() {
            {
                add(company);
            }
        });

    }

}
