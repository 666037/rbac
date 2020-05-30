package com.kad.rbac.security.filter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.kad.rbac.security.service.impl.RbacAccessDecisionManager;
import com.kad.rbac.security.service.impl.RbacFilterInvocationMetadataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RbacFilterSecurityInterceptor  extends AbstractSecurityInterceptor implements Filter {

    private static final String FILTER_APPLIED = "__spring_security_filter_filterApplied";
    @Autowired
    private RbacFilterInvocationMetadataSource securityMetadataSource;


           // urlMatcher.pathMatchesUrl(url, resURL)


    @Autowired
    public void setMyAccessDecisionManager(RbacAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getAttribute(FILTER_APPLIED) != null) {
           FilterInvocation fi = new FilterInvocation(request, response, chain);
            invoke(fi,false);
            return;
        }else

         request.setAttribute(FILTER_APPLIED,true);
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi,true);
    }


    public void invoke(FilterInvocation fi,boolean flag) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token=null;
        if(flag) {
            log.info("过滤器url:"+fi.getRequestUrl());
            token = super.beforeInvocation(fi);

        }
        try {
            //执行下一个拦截器
              {
                 fi.getChain().doFilter(fi.getRequest(), fi.getResponse());

             }
        } finally {
            if(flag)
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}

