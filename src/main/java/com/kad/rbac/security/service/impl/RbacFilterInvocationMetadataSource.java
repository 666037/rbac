package com.kad.rbac.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  * @Author: Xuanbu
 *  * @Date: 2020/4/23
 */
@Component
@Slf4j
public class RbacFilterInvocationMetadataSource implements FilterInvocationSecurityMetadataSource {
     @Autowired
    private AuthUserService authUserService;

     @Autowired
    RbacAntPathMatcherService antPathMatcherService;

    @Value(value = "${rbac.filter.pattern}")
    private String filterPattern;

    //private static final Logger log = LoggerFactory.getLogger(RbacFilterInvocationMetadataSource.class);

    /**
     * @Author: Xuanbu
     * @Description: 返回本次访问需要的权限，可以有多个权限
     * @Date: 2019/3/27-17:11
     * @Param: [o]
     * @return: java.util.Collection<org.springframework.security.access.ConfigAttribute>
     **/
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        FilterInvocation fi= (FilterInvocation) o;
        String requestUrl =fi.getHttpRequest().getRequestURI();
        if(antPathMatcherService.isMatchUrlDefaultConfig(fi.getHttpRequest()))//如果是白名单的url，直接返回null 放行
            return  null;

        Collection<ConfigAttribute>values=  authUserService
                .loadRoleIdsByUrl(requestUrl).stream().map(u->new SecurityConfig(u))
                .collect(Collectors.toList());
        log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, values);
       return values;

        /**
         * @Author: Xuanbu
         * @Description: 如果本方法返回null的话，意味着当前这个请求不需要任何角色就能访问
         * 此处做逻辑控制，如果没有匹配上的，返回一个默认具体权限，防止漏缺资源配置
         **/
      //  log.warn();
    //    return SecurityConfig.createList("ROLE_LOGIN");
    }

    /**
     * @Author: Xuanbu
     * @Description: 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
     * 如果不需要校验，这里实现方法，方法体直接返回null即可。
     * @Date: 2019/3/27-17:12
     * @Param: []
     * @return: java.util.Collection<org.springframework.security.access.ConfigAttribute>
     **/
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * @Author: Xuanbu
     * @Description: 方法返回类对象是否支持校验，
     * web项目一般使用FilterInvocation来判断，或者直接返回true
     * @Date: 2020/4/23
     * @Param: [aClass]
     * @return: boolean
     **/
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
