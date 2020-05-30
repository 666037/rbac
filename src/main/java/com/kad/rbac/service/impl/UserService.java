package com.kad.rbac.service.impl;

import com.kad.rbac.dal.UserDao;
import com.kad.rbac.dal.UserRoleDao;
import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.PageCommonResult;
import com.kad.rbac.model.common.PageQuery;
import com.kad.rbac.model.entity.po.UserDO;
import com.kad.rbac.model.entity.po.UserRoleDO;
import com.kad.rbac.model.entity.vo.QueryUserVO;
import com.kad.rbac.model.entity.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements com.kad.rbac.service.UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public PageCommonResult<UserVO> GetUserList(QueryUserVO query) {
        Integer totalcount = this.GetUserListCount(query);
        List<UserVO> res = new ArrayList<UserVO>();
        if (totalcount != null && totalcount > 0) {
            res = userDao.getUserList(query);

        }

        return PageCommonResult.build(res, totalcount, "获取列表成功", true, 0);
    }

    @Override
    public Integer GetUserListCount(QueryUserVO query) {
        return userDao.getUserListCount(query);
    }

    @Transactional
    @Override
    public CommonResult AddUserInfo(UserVO userVO) {
        UserRoleDO userRoleDO = new UserRoleDO();
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userVO, userRoleDO);
        BeanUtils.copyProperties(userVO, userDO);
        UserDO userDO2 = userDao.getUserByUsername(userVO.getUsername());
        if (null == userDO2) {
            if (userDao.AddList(new ArrayList<UserDO>() {{
                add(userDO);
            }}) > 0) {
                userRoleDao.AddUserRoleList(new ArrayList<UserRoleDO>() {{
                    add(userRoleDO);
                }});
                return CommonResult.build(null, "添加成功", true, 0);
            } else {
                throw new RuntimeException("添加失败");
            }


        } else {
            return CommonResult.build(null, userVO.getUsername() + " 该用户名已存在", false, 0);
        }

    }

    @Transactional
    @Override
    public CommonResult UpdateUserInfo(UserVO userVO) {
        UserRoleDO userRoleDO = new UserRoleDO();
        UserDO userDO = new UserDO();

        UserDO userDO2 = userDao.getUserByUsername(userVO.getUsername());
        BeanUtils.copyProperties(userVO, userRoleDO);
        BeanUtils.copyProperties(userVO, userDO2);
        if (null != userDO2) {
            userRoleDao.DeleteUserRoleList(userVO.getUsername());
            if (userDao.AddList(new ArrayList<UserDO>() {{
                add(userDO2);
            }}) > 0) {
                userRoleDao.AddUserRoleList(new ArrayList<UserRoleDO>() {{
                    add(userRoleDO);
                }});
                return CommonResult.build(null, "修改成功", true, 0);
            } else {
                throw new RuntimeException("修改失败");
            }


        } else {
            return CommonResult.build(null, userVO.getUsername() + " 该用户名不存在", false, 0);
        }
    }




}
