package com.kad.rbac.security.service.impl;


import com.kad.rbac.security.common.RespBean;
import com.kad.rbac.security.common.WebRespWrite;
import com.kad.rbac.security.util.CookieHelper;
import com.kad.rbac.security.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Xuanbu
 * @Date: 2020/4/23
 * @Description: 认证成功的处理
 **/
@Component
public class RbacAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private JwtTkenProviderService tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
       // RespBean respBean = RespBean.ok("登录成功!", HrUtils.getCurrentHr());
      //  RespBean respBean = RespBean.ok("登录成功!", "");
      //  new WebRespWrite().writeToWeb(response, respBean);
       // System.out.println("登录成功!");

        returnJson(response,authentication);
    }

    private void returnJson(HttpServletResponse response,Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CookieHelper.addCookie("RBAC_TOKEN",tokenProvider.createJwtToken(authentication),3600,response,"localhost");
        response.getWriter().println(JsonHelper.serialize(RespBean.ok("登录成功")));

    }
}
