package com.kad.rbac.security.service.impl;

import com.kad.rbac.security.common.RespBean;
import com.kad.rbac.security.common.WebRespWrite;
import com.kad.rbac.security.enums.SysErrorCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * ajax请求登录失效时返回错误响应码
 */
public class RbacAjaxLoginAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public RbacAjaxLoginAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws ServletException, IOException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            WebRespWrite.writeToWeb(response,RespBean.build().setMsg("登录失效请重新登录").setCode(SysErrorCode.LOGIN_EXPIRE.getCode()));

        } else {
            super.commence(request, response, authException);
        }
    }

}
