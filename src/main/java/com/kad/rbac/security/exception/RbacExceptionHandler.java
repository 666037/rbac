package com.kad.rbac.security.exception;

import com.kad.rbac.security.common.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class RbacExceptionHandler  implements HandlerExceptionResolver {

      static final String ERROR_VIEW = "common/error/500.html";
    static final String NOT_FOUND_VIEW = "common/error/404.html";
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e)throws Exception{
        e.printStackTrace();
        if(isAjax(request)){//是ajax请求
            return RespBean.error(e.getMessage());
        }else{//不是ajax请求
          ModelAndView mv = new ModelAndView();
          log.info(e.getMessage());
             mv.addObject("exception",e);
            mv.addObject("url",request.getRequestURL());//发生异常的路径
            if(Exception.class.equals(org.springframework.web.servlet.NoHandlerFoundException.class))
            {
                mv.setViewName(NOT_FOUND_VIEW);//指定找不到页面跳转的页面
            }else {
                mv.setViewName(ERROR_VIEW);//指定发生异常之后跳转页面
            }
            return mv;
        }
    }

    private boolean isAjax(HttpServletRequest request){//判断request是否是ajax请求
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( request.getHeader("X-Requested-With").toString()) );
    }



    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        // 异常处理逻辑 goes here
        log.info("got exception: {}", ex.getClass());

        {
            return new ModelAndView("common/error/500.html");
        }
    }



}
