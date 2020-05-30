package com.kad.rbac.controller;

import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.PageCommonResult;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.po.UserDO;
import com.kad.rbac.model.entity.vo.QueryUserVO;
import com.kad.rbac.model.entity.vo.UserVO;
import com.kad.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {


    @Autowired
    UserService userService;
    @RequestMapping(value = "/user/getUserList")
    public PageCommonResult<UserVO> getUserList(QueryUserVO query)
    {
       return userService.GetUserList(query);

    }
    @RequestMapping(value = "/user/addUserInfo",method = RequestMethod.POST)
    public CommonResult addUserInfo(@RequestBody UserVO userVO) {

return userService.AddUserInfo(userVO);

    }
    @RequestMapping(value = "/user/updateUserInfo",method = RequestMethod.POST)
    public CommonResult updateUserInfo(@RequestBody UserVO userVO) {

        return userService.UpdateUserInfo(userVO);

    }


}
