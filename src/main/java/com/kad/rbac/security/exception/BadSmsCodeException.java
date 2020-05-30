package com.kad.rbac.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 短信验证码错误异常
 */
public class BadSmsCodeException extends AuthenticationException {
    public BadSmsCodeException(String msg) {
        super(msg);
    }
}
