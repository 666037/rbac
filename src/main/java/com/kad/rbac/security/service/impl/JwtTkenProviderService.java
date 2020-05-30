package com.kad.rbac.security.service.impl;


import com.kad.rbac.security.model.JwtAuthParams;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 验证token用同样的密钥去解开token
 * 倘若能解开则表示该token是合法可用的，解析时有可能会抛出以下5个exception，可以分别catch处理log出日志，这里都统一处理了。
 *
 * 1）ExpiredJwtException token时效过期异常
 *
 * 2）UnsupportedJwtException 验证的token和期待的token格式不一样时，例如解析的是一个明文JWT而期待的是一个加密签名JWT时就会抛出这个异常。
 *
 * 3）MalformedJwtException 表示这不是一个正确方法创建的token。
 *
 * 4）SignatureException token签名验证失败异常
 *
 * 5）IllegalArgumentException token为null或者空异常
 */



@Component

public class JwtTkenProviderService {

    Logger logger = LoggerFactory.getLogger(JwtTkenProviderService.class);

    @Autowired

    private JwtAuthParams authParameters;

    /**

     * Generate token for user login.

     *

     * @param authentication

     * @return return a token string.

     */

    public String createJwtToken(Authentication authentication) {

        //user name

        String username = ((com.kad.rbac.security.model.AuthUserDetail) authentication.getPrincipal()).getUsername();

        //expire time

        Date expireTime =new Date(System.currentTimeMillis()+authParameters.getTokenExpiredMs());

        //create token

        String token = Jwts.builder()

                .setSubject(username)

                .setExpiration(expireTime)

                .setIssuedAt(new Date())

                .signWith(SignatureAlgorithm.HS512, authParameters.getJwtTokenSecret())

                .compact();

        return token;

    }

    /**

     * validate token eligible.

     * if Jwts can parse the token string and no throw any exception, then the token is eligible.

     * @param token a jws string.

     */

    public boolean validateToken(String token) {

        String VALIDATE_FAILED ="validate failed : ";

        try {
            Jwts.parser()
                    .setSigningKey(authParameters.getJwtTokenSecret())
                    .parseClaimsJws(token);

            return true;

        }catch (Exception ex) {

            //ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, //IllegalArgumentException

            logger.error(VALIDATE_FAILED + ex.getMessage());

            return false;

        }

    }

}
