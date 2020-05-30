package com.kad.rbac.service;
import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.PageCommonResult;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.po.UserDO;
import com.kad.rbac.model.entity.vo.QueryUserVO;
import com.kad.rbac.model.entity.vo.UserVO;

import java.util.List;

public interface UserService {


      PageCommonResult<UserVO> GetUserList(QueryUserVO query);

       Integer GetUserListCount(QueryUserVO query);


      public  CommonResult AddUserInfo(UserVO userVO);
    public  CommonResult UpdateUserInfo(UserVO userVO);
}
