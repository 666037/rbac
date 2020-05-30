package com.kad.rbac.security.service.impl;


import com.kad.rbac.security.common.RespBean;
import com.kad.rbac.security.common.WebRespWrite;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Xuanbu
 * @Date: 2020/4/23
 * @Description: 认证失败的处理
 **/
@Component
public class RbacAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        RespBean respBean;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            respBean = RespBean.error("账户名或者密码输入错误!");
        } else if (exception instanceof LockedException) {
            respBean = RespBean.error("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            respBean = RespBean.error("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            respBean = RespBean.error("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            respBean = RespBean.error("账户被禁用，请联系管理员!");
        } else {
            respBean = RespBean.error(exception.getMessage());
        }
        response.setStatus(401);
         WebRespWrite.writeToWeb(response, respBean);
    }
}
