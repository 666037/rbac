package com.kad.rbac.model.common;

import lombok.Data;

@Data
public class GenericCommonResult<T> extends CommonResult {
    private T result;

    public static <T> GenericCommonResult<T> build(T result,String msg,boolean isSuccess,int code)
    {
        GenericCommonResult<T>temp=new GenericCommonResult<T>();
        temp.setResult(result);
        temp.setSuccess(isSuccess);
        temp.setMsg(msg);
        temp.setCode(code) ;
        return temp;
    }
}
