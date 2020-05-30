package com.kad.rbac.controller;

import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.common.PageCommonResult;
import com.kad.rbac.model.entity.po.RoleDO;
import com.kad.rbac.model.entity.po.UserDO;
import com.kad.rbac.model.entity.vo.QueryRoleVO;
import com.kad.rbac.model.entity.vo.QueryUserVO;
import com.kad.rbac.model.entity.vo.RightVO;
import com.kad.rbac.model.entity.vo.RoleVO;
import com.kad.rbac.security.SecurityController;
import com.kad.rbac.security.model.AuthUserDetail;
import com.kad.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController extends SecurityController {


//@RequestMapping(value = "/test", method = RequestMethod.GET)
//    public  String TestAuthInfo()
//    {
//        AuthUserDetail detail= super.getAuthCurrentUser();
//           System.out.println(detail);
//         //  return String.format("%s    %s    %s",detail.getUsername(),detail.getRoleName(),detail.getRoleId());
//    }
    @Autowired
    RoleService roleService;
    @RequestMapping(value = "/role/getRoleList")
    public PageCommonResult<RoleVO> getRoleList(QueryRoleVO query)
    {
        return roleService.GetRoleList(query);
    }


}
