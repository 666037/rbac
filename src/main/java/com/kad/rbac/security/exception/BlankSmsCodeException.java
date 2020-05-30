package com.kad.rbac.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 短信为空异常
 */
public class BlankSmsCodeException extends AuthenticationException {
    public BlankSmsCodeException(String msg) {
        super(msg);
    }
}