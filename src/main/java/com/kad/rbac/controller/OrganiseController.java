package com.kad.rbac.controller;

import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.OrganiseDO;
import com.kad.rbac.model.entity.po.OrganiseRoleDO;
import com.kad.rbac.model.entity.vo.OrganiseVO;
import com.kad.rbac.model.entity.vo.QueryOrganiseVO;
import com.kad.rbac.service.OrganiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class OrganiseController {

    @Autowired
    OrganiseService organiseService;

    @RequestMapping(value = "/organise/getTreeOrganiseList")
    public GenericCommonResult<List<OrganiseVO>> GetTreeOrganiseList(QueryOrganiseVO v) {

        GenericCommonResult<List<OrganiseVO>> res=organiseService.GetTreeOrganiseList(v);
        return res;

    }
    @RequestMapping(value = "/organise/getOrganiseList")
    public GenericCommonResult<List<OrganiseVO>> getOrganiseList(QueryOrganiseVO v) {

        GenericCommonResult<List<OrganiseVO>> res=organiseService.GetOrganiseList(v);
        return res;
    }

    @RequestMapping(value = "/organise/addOrganise",method = RequestMethod.POST)
    public CommonResult addOrganise(@RequestBody OrganiseDO organise) {

        return organiseService.AddList(new ArrayList<OrganiseDO>() {
            {
                add(organise);
            }
        });

    }
    @RequestMapping(value = "/organise/updateOrganise",method = RequestMethod.POST)
    public CommonResult updateOrganise(@RequestBody OrganiseDO organise) {

        return organiseService.AddList(new ArrayList<OrganiseDO>() {
            {
                add(organise);
            }
        });

    }

    @RequestMapping(value = "/organise/getOrganiseRoleListByOrganiseId")
    public GenericCommonResult<List<OrganiseRoleDO>> getRoleList(Integer organiseId)
    {
        return organiseService.getOrganiseRoleListByOrganiseId(organiseId);
    }


}
