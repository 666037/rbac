package com.kad.rbac.security.service.impl;

import com.kad.rbac.security.exception.BadVerifyCodeException;
import com.kad.rbac.security.exception.BlankVerifyCodeException;
import com.kad.rbac.security.model.AuthUserDetail;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义认证器
 */
@Component

public class RbacAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {//implements AuthenticationProvider {
    @Autowired
    private AuthUserService userDetailsService;

     Logger log = LoggerFactory.getLogger(JwtTkenProviderService.class);
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //用户输入的用户名
        String username = authentication.getName();
        //用户输入的密码
        String password = authentication.getCredentials().toString();
        //通过CustomWebAuthenticationDetails获取用户输入的验证码信息
        RbacWebAuthenticationDetails details = (RbacWebAuthenticationDetails) authentication.getDetails();

        log.info("验证码："+details.getVerifyCode()+"  "+details.getSmsCode());
        String verifyCode = details.getVerifyCode();
        verifyCode="123";
        if(null == verifyCode || verifyCode.isEmpty()){
            log.warn("未输入图形验证码");
            throw new BlankVerifyCodeException("请输入图形验证码");
        }
        //校验验证码
        if(!validateVerifyCode(verifyCode)){
            log.warn("验证码输入错误");
            throw new BadVerifyCodeException("验证码输入错误");
        }
        //通过自定义的CustomUserDetailsService，以用户输入的用户名查询用户信息
        AuthUserDetail userDetails = (AuthUserDetail) userDetailsService.loadUserByUsername(username);
        //校验用户密码
        if(!userDetails.getPassword().equals(password)){
            log.warn("密码错误");
            throw new BadCredentialsException("密码错误");
        }
        Object principalToReturn = userDetails;
        //将用户信息塞到SecurityContext中，方便获取当前用户信息
        return this.createSuccessAuthentication(principalToReturn, authentication, userDetails);
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return null;
    }

    /**
     * 验证用户输入的验证码
     * @param inputVerifyCode
     * @return
     */
    public boolean validateVerifyCode(String inputVerifyCode){
        //获取当前线程绑定的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 这个VerifyCodeFactory.SESSION_KEY是在servlet中存入session的名字
      //  HttpSession session = request.getSession();
     //   String verifyCode = (String)session.getAttribute(VerifyCodeUtil.SESSION_KEY);
        String verifyCode="123";
        if(null == verifyCode || verifyCode.isEmpty()){
            log.warn("验证码过期请重新验证");
            throw new DisabledException("验证码过期，请重新验证");
        }
        // 不分区大小写
        verifyCode = verifyCode.toLowerCase();
        inputVerifyCode = inputVerifyCode.toLowerCase();

        log.info("验证码：{}, 用户输入：{}", verifyCode, inputVerifyCode);

        return verifyCode.equals(inputVerifyCode);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
