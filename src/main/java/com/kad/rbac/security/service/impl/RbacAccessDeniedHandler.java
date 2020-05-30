package com.kad.rbac.security.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kad.rbac.security.common.RespBean;
import com.kad.rbac.security.common.WebRespWrite;
import com.kad.rbac.security.enums.SysErrorCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Xuanbu
 * @Date: 2020/4/23
 * @Description: Denied是拒签的意思
 * 此处我们可以自定义403响应的内容,让他返回我们的错误逻辑提示
 **/
@Component
public class RbacAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp,
                       AccessDeniedException e) throws IOException {
        if ("XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With"))) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            WebRespWrite.writeToWeb(resp, RespBean.build().setMsg("无权访问此接口").setCode(SysErrorCode.ACCESS_DENIED.getCode()));
        } else {
        //   super.commence(request, response, authException);
            resp.sendRedirect("/rbac/error/403.html");
        }

    }
}
