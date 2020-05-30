package com.kad.rbac.service.impl;

import com.kad.rbac.dal.RoleDao;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.common.PageCommonResult;
import com.kad.rbac.model.entity.po.RoleDO;
import com.kad.rbac.model.entity.po.RoleDO;
import com.kad.rbac.model.entity.vo.QueryRoleVO;
import com.kad.rbac.model.entity.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleService implements com.kad.rbac.service.RoleService {


    @Autowired
    RoleDao roleDao;

    @Override
    public PageCommonResult<RoleVO> GetRoleList(QueryRoleVO query) {
        Integer totalcount=this.GetRoleListCount(query);
        List<RoleVO> res=new ArrayList<RoleVO>();
        if(totalcount!=null&&totalcount>0) {
            res = roleDao.getRoleList(query);

        }else
        {
            totalcount=0;
        }

        return PageCommonResult.build(res,totalcount,"获取列表成功",true,0);
    }

    @Override
    public Integer GetRoleListCount(QueryRoleVO query) {
        return roleDao.getRoleListCount(query);
    }


    @Override
    public GenericCommonResult<List<Integer>> getRoleRightIdList(String roleId) {
        return   GenericCommonResult.build( roleDao.getRoleRightIdList(roleId),"保存成功",true,0);

    }


}
