package com.kad.rbac.security.enums;

import lombok.Getter;

@Getter
/**
 * 系统级错误码
 */
public enum SysErrorCode {
    LOGIN_SUCCESS(1000,"登录成功"),
    LOGIN_FAIL(1001,"登录失败"),
    LOGIN_EXPIRE(1002,"登录失效"),
    ACCESS_DENIED(1003,"拒绝访问"),;



    private Integer code;
    private String msg;

    SysErrorCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
