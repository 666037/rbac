package com.kad.rbac.security.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

    /**
     * 简单cookie封装
     * @param key k
     * @param value v
     * @param lifecycle 生命周期
     * @param response res
     */
    public static void addCookie(String key, String value, int lifecycle, HttpServletResponse response,String domain){
        Cookie cookie = new Cookie(key,value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(lifecycle);
        response.addCookie(cookie);
    }

    /**
     * 获取http请求中的对应cookie
     * @param request
     * @param key
     * @return
     */
    public static String getCookie(HttpServletRequest request, String key){
                if(request == null || StringUtils.isEmpty(key)){
                         return "";
                      }
                Cookie[] cookies = request.getCookies();
                if(cookies!=null) {
                    for (Cookie cookie : cookies) {
                        if (key.equals(cookie.getName())) {
                            return cookie.getValue();
                        }
                    }
                }
               return "";
           }
}
