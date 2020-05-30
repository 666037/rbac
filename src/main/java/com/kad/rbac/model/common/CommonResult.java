package com.kad.rbac.model.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class CommonResult {

    public CommonResult()
    {
        attachData = new HashMap<String, Object>();
    }
    /// 是否成功
    private boolean success;
    /// 返回给客户端的信息
    private String msg;
    /// 错误堆栈
    private String stackTrace ;
    ///业务响应码
    private Integer code;
    /// 附属结果
    private HashMap<String, Object> attachData ;
    public static CommonResult build(HashMap<String, Object> attachData ,String msg,boolean isSuccess,int code)
    {
        CommonResult temp=new CommonResult();
        temp.setAttachData(attachData);
        temp.setSuccess(isSuccess);
        temp.setMsg(msg);

        temp.setCode(code) ;
        return temp;
    }
}


