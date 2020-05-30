package com.kad.rbac.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 图形验证码为空异常
 */
public class BlankVerifyCodeException  extends AuthenticationException {
    public BlankVerifyCodeException(String msg) {
        super(msg);
    }
}