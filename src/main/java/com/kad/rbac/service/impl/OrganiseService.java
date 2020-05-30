package com.kad.rbac.service.impl;

import com.kad.rbac.dal.OrganiseDao;
import com.kad.rbac.dal.OrganiseRoleDao;
import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.OrganiseDO;
import com.kad.rbac.model.entity.po.OrganiseRoleDO;
import com.kad.rbac.model.entity.vo.OrganiseVO;
import com.kad.rbac.model.entity.vo.QueryOrganiseVO;
import com.kad.rbac.model.entity.vo.RightVO;
import com.kad.rbac.util.TreeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrganiseService implements com.kad.rbac.service.OrganiseService {

@Autowired
    OrganiseDao organiseDao;
@Autowired
    OrganiseRoleDao organiseRoleDao;
    @Override
    public GenericCommonResult<List<OrganiseVO>> GetOrganiseList(QueryOrganiseVO vo) {
        List<OrganiseVO>res=organiseDao.GetOrganiseList( vo);
        return GenericCommonResult.build(res,"获取成功",true,0);
    }

    @Override
    public GenericCommonResult<List<OrganiseVO>> GetTreeOrganiseList(QueryOrganiseVO vo) {
        List<OrganiseVO>res=organiseDao.GetOrganiseList(vo);
        res=(List<OrganiseVO>) TreeHelper.ConvertToTree(res);
        return GenericCommonResult.build(res,"获取成功",true,0);
    }



    @Override
    public CommonResult AddList(List<OrganiseDO> list) {
        int count= AddTargetList(list);
        return CommonResult.build(null,"保存成功",count>0,0);
    }


    @Override
    public int AddTargetList(List<OrganiseDO> list) {
        return organiseDao.AddTargetList(list);
    }

    public GenericCommonResult<List<OrganiseRoleDO>>  getOrganiseRoleListByOrganiseId(Integer organiseId)
    {
      return   GenericCommonResult.build(organiseRoleDao.getOrganiseRoleListByOrganiseId(organiseId),"获取成功",true,0)  ;

    }

}
