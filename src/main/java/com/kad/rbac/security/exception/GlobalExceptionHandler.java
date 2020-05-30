package com.kad.rbac.security.exception;

import com.kad.rbac.security.common.RespBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class.getName());
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultHandler(HttpServletRequest request, Exception e) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", e);
//        modelAndView.addObject("url", request.getRequestURL());
//        modelAndView.setViewName("error");
//        return modelAndView;
//    }

    /**
     * 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("错误");

        return new ResponseEntity<Object>("出错了", status);

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object jsonHandler(HttpServletRequest request, Exception e) throws Exception {

        log(e, request);
        if(isAjax(request))
        {  return RespBean.error(e.toString(),request.getRequestURL().toString()).setCode(500);

        }else
        {
            return new ModelAndView("common/error/500.html");
        }

    }

    private void log(Exception ex, HttpServletRequest request) {
        logger.error("************************异常开始*******************************");
//        if(getUser() != null)
//            logger.error("当前用户id是" + getUser().getUserId());
        logger.error(ex);
        logger.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }

        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            logger.error(stackTraceElement.toString());
        }
        logger.error("************************异常结束*******************************");
    }

    private boolean isAjax(HttpServletRequest request){//判断request是否是ajax请求
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( request.getHeader("X-Requested-With").toString()) );
    }
}
