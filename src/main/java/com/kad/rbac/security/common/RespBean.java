package com.kad.rbac.security.common;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class RespBean {

    private Integer status;   //状态码
    private String msg;       //返回信息
    private Object obj;       //数据
    private Integer code;   //前端业务状态码
    private RespBean() {
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
        this.code=code;
    }
   public  RespBean  setCode(Integer code)
   {
       this.code=code;
       return  this;
   }
    public Integer getStatus() {

        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
    public static boolean isAjax(HttpServletRequest request){//判断request是否是ajax请求
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( request.getHeader("X-Requested-With").toString()) );
    }

}
