package com.kad.rbac.security.service.impl;

import com.kad.rbac.security.common.RespBean;
import com.kad.rbac.security.common.WebRespWrite;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Xuanbu
 * @Date: 2020/4/23
 * @Description: 注销登录处理
 **/
@Component
public class RbacLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws  ServletException, IOException {

        RespBean respBean = RespBean.ok("注销成功!");
         WebRespWrite.writeToWeb(response, respBean);
        System.out.println("注销成功!");
    }
}
