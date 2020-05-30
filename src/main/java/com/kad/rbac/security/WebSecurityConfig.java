package com.kad.rbac.security;

import com.kad.rbac.security.filter.RbacFilterSecurityInterceptor;
import com.kad.rbac.security.filter.RbacJwtAuthenticationFilter;
import com.kad.rbac.security.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //全局
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthUserService hrService;  //实现了UserDetailsService接口
//    @Autowired
//    private RbacFilterInvocationMetadataSource filterMetadataSource; //权限过滤器（当前url所需要的访问权限）
    @Autowired
    private RbacAccessDecisionManager myAccessDecisionManager;//权限决策器
    @Autowired
    private RbacAccessDeniedHandler deniedHandler;//自定义错误(403)返回数据

    @Autowired
    private RbacAuthenticationFailureHandler authenticationFailureHandler;//身份验证失败


    @Autowired
    private RbacAuthenticationSuccessHandler authenticationSuccessHandler;//身份验证成功
    @Autowired
    private RbacLogoutSuccessHandler logoutSuccessHandler;//退出登录
    @Autowired
    private RbacJwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private RbacAuthenticationProvider rbacAuthenticationProvider;

    @Autowired
     private RbacAuthenticationDetailsSource authenticationDetailsSource;


    @Autowired
     private RbacFilterSecurityInterceptor  filterSecurityInterceptor;

    @Value(value = "${rbac.filter.pattern}")
    private String filterPattern;

    /**
     * @Author: Xuanbu
     * @Date: 2020/4/23
     * @Description: 配置userDetails的数据源，密码加密格式
     * @Param: [auth]
     * @return: void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(rbacAuthenticationProvider);//使用自定义的验证器  验证用户名和密码或其他需要验证的内容

//        auth.userDetailsService(hrService)
//                .passwordEncoder(new BCryptPasswordEncoder(4));

    }

    /**
     * @Author: Xuanbu
     * @Date: 2020/4/23
     * @Description: 配置放行的资源
     * @Param: [web]
     * @return: void
     **/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(filterPattern.split(","));

    }

    /**
     * @Author: XuanBu
     * @Date: 2020/4/23
     * @Description: HttpSecurity包含了原数据（主要是url）
     * 通过withObjectPostProcessor将MyFilterInvocationSecurityMetadataSource和MyAccessDecisionManager注入进来
     * 此url先被MyFilterInvocationSecurityMetadataSource处理，然后 丢给 MyAccessDecisionManager处理
     * 如果不匹配，返回 MyAccessDeniedHandler
     * @Param: [http]
     * @return: void
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
               .exceptionHandling().authenticationEntryPoint(new RbacAjaxLoginAuthenticationEntryPoint("/login"))
                 .and()
                .headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable().and()
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                 .addFilterAfter(filterSecurityInterceptor,FilterSecurityInterceptor.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/verifylogin")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(authenticationFailureHandler )
                .successHandler(authenticationSuccessHandler)
                .authenticationDetailsSource(authenticationDetailsSource)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll()
                .and().csrf().disable().authorizeRequests()
                       .anyRequest().authenticated().and()
               .exceptionHandling().accessDeniedHandler(deniedHandler).and()

        ;
    }
}
