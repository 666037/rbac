package com.kad.rbac.controller;


import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.vo.RightVO;
import com.kad.rbac.model.entity.vo.RoleRightVO;
import com.kad.rbac.security.SecurityController;
import com.kad.rbac.security.model.Right;
import com.kad.rbac.service.RightService;
import com.kad.rbac.service.RoleService;
import com.kad.rbac.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RightController extends SecurityController {

    @Autowired
    RightService rightService;
    @Autowired
    RoleService roleService;
    @RequestMapping(value = "/right/getTreeMenuList")
    public GenericCommonResult<List<RightVO>> getTreeMenuList() {
       List<Integer>roles= super.getAuthCurrentUser().getRights().stream().map(u->u.getRoleId()).collect(Collectors.toList());
        GenericCommonResult<List<RightVO>> res=rightService.GetTreeMenuList(roles);
        return res;

    }
    @RequestMapping(value = "/right/getMenuList")
    public GenericCommonResult<List<RightVO>> getMenuList() {
        List<Integer>roles= super.getAuthCurrentUser().getRights().stream().map(u->u.getRoleId()).collect(Collectors.toList());
        GenericCommonResult<List<RightVO>> res=rightService.GetMenuList(roles);
        return res;
    }
    @RequestMapping(value = "/right/getRightList")
    public GenericCommonResult<List<RightVO>> getRightList() {

        GenericCommonResult<List<RightVO>> res=rightService.GetRightList();
        return res;
    }

    @RequestMapping(value = "/right/getRoleRightIdList")
    public GenericCommonResult<List<Integer>> getRoleRightIdList(String roleId) {
        return roleService.getRoleRightIdList(roleId);
    }
    @RequestMapping(value = "/right/addRight",method = RequestMethod.POST)
    public CommonResult addRight(@RequestBody RightDO right) {

        return rightService.AddList(new ArrayList<RightDO>() {
            {
                add(right);
            }
        });

    }
    @RequestMapping(value = "/right/updateRight",method = RequestMethod.POST)
    public CommonResult updateRight(@RequestBody RightDO right) {

        return rightService.AddList(new ArrayList<RightDO>() {
            {
                add(right);
            }
        });

    }

    @RequestMapping(value = "/right/addRoleRight",method = RequestMethod.POST)
    public CommonResult addRoleRight(@RequestBody RoleRightVO roleRight) {

        return rightService.AddRoleRight(roleRight);

    }
    @RequestMapping(value = "/right/updateRoleRight",method = RequestMethod.POST)
    public CommonResult updateRoleRight(@RequestBody RoleRightVO roleRight) {

        return rightService.UpdateRoleRight(roleRight);

    }




}
