package com.kad.rbac.security.filter;

import com.kad.rbac.security.model.JwtAuthParams;
import com.kad.rbac.security.service.impl.AuthUserService;
import com.kad.rbac.security.service.impl.JwtTkenProviderService;
import com.kad.rbac.security.service.impl.RbacAntPathMatcherService;
import com.kad.rbac.security.util.CookieHelper;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class RbacJwtAuthenticationFilter extends OncePerRequestFilter {



    private Logger logger = LoggerFactory.getLogger(RbacJwtAuthenticationFilter.class);

    @Autowired
    private JwtTkenProviderService jwtTokenProvider;

    @Autowired
    private JwtAuthParams authParameters;

    @Autowired
    private AuthUserService userService;
    @Autowired
    RbacAntPathMatcherService antPathMatcherService;
    //1.从每个请求header获取token
    //2.调用前面写的validateToken方法对token进行合法性验证
    //3.解析得到username，并从database取出用户相关信息权限
    //4.把用户信息(role等)以UserDetail形式放进SecurityContext以备整个请求过程使用。
    // （例如哪里需要判断用户权限是否足够时可以直接从SecurityContext取出去check）
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        if(!antPathMatcherService.isMatchUrlDefaultConfig(request))//如果是白名单的url 放行
        {

            String token = getJwtFromRequest(request);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                String username = getUsernameFromJwt(token, authParameters.getJwtTokenSecret());

                UserDetails userDetails = userService.loadUserByUsername(username);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                logger.error(request.getParameter("username") + " :Token is null");
            }
        }
        super.doFilter(request, response, filterChain);
    }

    /**
     * Get Bear jwt from request header Authorization.
     *
     * @param request servlet request.
     * @return token or null.
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String token = CookieHelper.getCookie(request,"RBAC_TOKEN");
        if (token != null&& !StringUtils.isEmpty(token)) {
            return token;
        }
        return null;
    }

    /**
     * Get user name from Jwt, the user name have set to jwt when generate token.
     *
     * @param token jwt token.
     * @param signKey jwt sign key, set in properties file.
     * @return user name.
     */
    private String getUsernameFromJwt(String token, String signKey) {
        return Jwts.parser().setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
