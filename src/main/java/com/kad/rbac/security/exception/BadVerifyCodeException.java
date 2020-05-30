package com.kad.rbac.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 图形验证码错误
 */
public class BadVerifyCodeException extends AuthenticationException {
    public BadVerifyCodeException(String msg) {
        super(msg);
    }
}