package com.kad.rbac.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
@Service
@Slf4j
public class RbacAntPathMatcherService {

    @Value(value = "${rbac.filter.pattern}")
    private String filterPattern;
    public boolean isMatchUrlDefaultConfig(HttpServletRequest request) {

        return MatchUrlConfig(filterPattern, request);
    }
   public boolean isMatchUrlConfig(String matchPattern, HttpServletRequest request) {
       if (matchPattern.isEmpty() || null == matchPattern) {
           return MatchUrlConfig(filterPattern, request);
       } else {
           return MatchUrlConfig(matchPattern, request);
       }

   }

    private boolean MatchUrlConfig(String matchPattern, HttpServletRequest request)
    {
        Collection<AntPathRequestMatcher> matchers=null;
        boolean flag=false;
        if(!matchPattern.isEmpty())
        {
            String[]patterns=  matchPattern.split(",");
            if(patterns.length>0)
            {
                matchers= Arrays.asList(patterns).stream().map(u->{
                    return new AntPathRequestMatcher(u);
                }).collect(Collectors.toList());
                for (AntPathRequestMatcher match :matchers) {
                    if(match.matches(request))
                    {
                        flag=true;
                        log.info("匹配成功"+request.getRequestURL()+"跳过");
                        return  flag;
                    }
                }
            }
        }
        return  flag;
    }
}
