package com.kad.rbac.service.impl;

import com.kad.rbac.dal.OrganiseRoleDao;
import com.kad.rbac.dal.RightDao;
import com.kad.rbac.dal.RoleDao;
import com.kad.rbac.dal.RoleRightDao;
import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import com.kad.rbac.model.entity.po.OrganiseRoleDO;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.po.RoleDO;
import com.kad.rbac.model.entity.po.RoleRightDO;
import com.kad.rbac.model.entity.vo.RoleRightVO;
import com.kad.rbac.model.entity.vo.RightVO;
import com.kad.rbac.util.TreeHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RightService implements com.kad.rbac.service.RightService {


    @Autowired
    RightDao rightDao;
    @Autowired
    RoleDao roleDao;

    @Autowired
    RoleRightDao roleRightDao;
    @Autowired
    OrganiseRoleDao organiseRoleDao;

//DeleteRoleRightList
    @Override
    public GenericCommonResult<List<RightVO>> GetTreeMenuList(List<Integer>roles) {
        List<RightVO>res=rightDao.GetMenuList(roles);

        res=(List<RightVO>)TreeHelper.ConvertToTree(res);


       return GenericCommonResult.build(res,"获取菜单成功",true,0);
    }

    @Override
    public CommonResult AddList(List<RightDO> list) {
        int count= AddTargetList(list);
        return CommonResult.build(null,"保存成功",count>0,0);
    }

    @Transactional
    @Override
    public CommonResult AddRoleRight(RoleRightVO roleRightVO) {

      if(roleRightVO.getRightIds().size()>0)
      {
          Integer maxRoleId=roleDao.getMaxRoleId();
          RoleDO roleDO=new RoleDO();
          OrganiseRoleDO organiseRoleDO=new OrganiseRoleDO();
          BeanUtils.copyProperties(roleRightVO, roleDO);
          BeanUtils.copyProperties(roleRightVO, organiseRoleDO);
          roleDO.setRoleId(maxRoleId+new Random(UUID.randomUUID().hashCode()).ints(1,100).findFirst().getAsInt());
          Integer t= roleDao.AddRoleList(new ArrayList<RoleDO>() {{add(roleDO);}});
          if(t>0) {
              List<RoleRightDO>roleRightList=new ArrayList<RoleRightDO>();
              roleRightVO.getRightIds().stream().map(u -> {
                  int rid=u.intValue();
                  int roleId=roleDO.getRoleId().intValue();
                  RoleRightDO o= new RoleRightDO();
                  o.setRoleId(roleId);
                  o.setRightId(rid);
                  roleRightList.add(o);
                  return null;
              }).collect(Collectors.toList());
              roleRightDao.AddRoleRightList(roleRightList);
              organiseRoleDO.setRoleId(roleDO.getRoleId());
              organiseRoleDao.AddOrganiseRoleList(new ArrayList<OrganiseRoleDO>(){{add(organiseRoleDO);}});
              return CommonResult.build(null, "添加成功", true, 0);
          }else
          {
              return CommonResult.build(null, "添加失败，请重试", false, 0);
          }

      }else {

        return   CommonResult.build(null, "权限不能为空", false, 0);
      }



    }
    @Transactional
    @Override
    public CommonResult UpdateRoleRight(RoleRightVO roleRightVO) {
        if(roleRightVO.getRightIds().size()>0)
        {
          //  Integer maxRoleId=roleDao.getMaxRoleId();
            RoleDO roleDO=new RoleDO();
            BeanUtils.copyProperties(roleRightVO, roleDO);
            OrganiseRoleDO organiseRoleDO=organiseRoleDao.getOrganiseRoleList(roleRightVO.getRoleId()).get(0);
           if(null!=organiseRoleDO&&!organiseRoleDO.getOrganiseId().equals(roleRightVO.getOrganiseId()))
           {
               organiseRoleDO.setOrganiseId(roleRightVO.getOrganiseId());
               organiseRoleDao.AddOrganiseRoleList(new ArrayList<OrganiseRoleDO>(){{add(organiseRoleDO);}});
           }
            Integer t= roleDao.AddRoleList(new ArrayList<RoleDO>() {{add(roleDO);}});
            if(t>0&&roleRightDao.DeleteRoleRightList(roleRightVO.getRoleId())>0&&null!=organiseRoleDO) {
                List<RoleRightDO>roleRightList=new ArrayList<RoleRightDO>();
                roleRightVO.getRightIds().stream().map(u -> {
                    int rid=u.intValue();
                    int roleId=roleDO.getRoleId().intValue();
                    RoleRightDO o= new RoleRightDO();
                    o.setRoleId(roleId);
                    o.setRightId(rid);
                    roleRightList.add(o);
                    return null;
                }).collect(Collectors.toList());

                roleRightDao.AddRoleRightList(roleRightList);
                return CommonResult.build(null, "修改成功", true, 0);
            }else
            {
                throw  new RuntimeException("修改失败，请重试");
               // return CommonResult.build(null, "修改失败，请重试", false, 0);
            }

        }else {
            throw  new RuntimeException("权限不能为空");
           // return   CommonResult.build(null, "权限不能为空", false, 0);
        }



    }


    @Override
    public GenericCommonResult<List<RightVO>> GetMenuList(List<Integer>roles) {
        List<RightVO>res=rightDao.GetMenuList(roles);
        return GenericCommonResult.build(res,"获取菜单成功",true,0);
    }
    @Override
    public GenericCommonResult<List<RightVO>> GetRightList() {
        List<RightVO>res=rightDao.GetRightList();
        res=(List<RightVO>)TreeHelper.ConvertToTree(res);
        return GenericCommonResult.build(res,"获取权限成功",true,0);
    }
    @Override
    public int AddTargetList(List<RightDO> list) {
      return   rightDao.AddRightList(list);
    }
}
