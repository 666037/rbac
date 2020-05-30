package com.kad.rbac.security.service.impl;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义验证信息源注入实现类（将验证信息源注入到验证信息类）
 */
@Component
public class RbacAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
      @Override
      public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
                  return new RbacWebAuthenticationDetails(request);
             }
  }
