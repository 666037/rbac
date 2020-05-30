package com.kad.rbac.security.exception;

import com.kad.rbac.security.common.RespBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FinalExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Object error(HttpServletResponse resp, HttpServletRequest req) {
        // 错误处理逻辑
        if (RespBean.isAjax(req)) {
            return RespBean.build().setMsg(req.getRequestURL().toString() + " 该接口不存在")
                    .setObj(req.getRequestURL().toString()).setCode(404).setStatus(404);

        } else {
            return new ModelAndView("common/error/" + resp.getStatus() + ".html");
        }

    }
}

