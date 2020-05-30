package com.kad.rbac.security.service.impl;

import com.kad.rbac.security.dal.AuthUserDal;
import com.kad.rbac.security.model.AuthUserDetail;
import com.kad.rbac.security.model.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:  xuanbu
 * @Description: 实现了UserDetailsService接口中的loadUserByUsername方法
 * 执行登录,构建Authentication对象必须的信息,
 * 如果用户不存在，则抛出UsernameNotFoundException异常
 * @Date: 2020/4/23
 * @Param: [s]
 * @return: org.springframework.security.core.userdetails.UserDetails
 **/
@Service
@Transactional
public class AuthUserService implements UserDetailsService {

    @Autowired
    AuthUserDal authUserDal;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        /**
         * @Author: Xuanbu
         * @Description: 查询数据库，获取登录的用户信息
         **/
        AuthUserDetail hr = this.loadUser(s);


        if (hr == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return hr;
    }

    public AuthUserDetail loadUser(String UserName)//加载一些权限信息
    {
        List<AuthUserDetail> hr= authUserDal.loadUserByUsername(UserName);
        if(hr==null||hr.isEmpty())
        {
            return  null;
        }
        AuthUserDetail authUserDetail=new AuthUserDetail();
        authUserDetail.setUsername(hr.get(0).getUsername());
        authUserDetail.setPassword(hr.get(0).getPassword());
        authUserDetail.setRights(hr.stream().map(u->
                {
                    Right r=new Right();
                    r.setRoleId(u.getRoleId());
                    r.setRoleName(u.getRoleName());
                    return  r;
                }
        ).collect(Collectors.toList()));
        return  authUserDetail;

    }

    /**
     * 根据访问url获取所需角色id集合
     * @param Url
     * @return
     */
    public List<String> loadRoleIdsByUrl(String Url)//加载一些权限信息
    {
        return authUserDal.loadRoleIdsByUrl(Url.toLowerCase());//统一转换为小写查询

    }


}


