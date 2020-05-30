package com.kad.rbac.security.service.impl;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义验证请求详细信息类（包括图形验证码，短信验证码）一同带入AuthenticationProvider中
 */
public class RbacWebAuthenticationDetails  extends WebAuthenticationDetails {

    private static final long serialVersionUID = 6975601077710753878L;
    /**
     * 图形验证码
     */
    private final String verifyCode;
    /**
     * 短信验证码
     */
    private  final  String smsCode;
    public RbacWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verifyCode = request.getParameter("verifyCode");
        smsCode=request.getParameter("smsCode");
    }

    public String getVerifyCode() {
        return verifyCode;
    }
    public String getSmsCode() {
        return smsCode;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; verifyCode: ").append(this.getVerifyCode());
        return sb.toString();
    }
}
