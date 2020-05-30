package com.kad.rbac.model.common;

import lombok.Data;

import java.util.Collection;

@Data
public class PageCommonResult<T> extends CommonResult {

    public PageCommonResult()
    {

    }

    /**
     * 总数量
     */
    private int count;


    /// <summary>
    /// 结果集
    /// </summary>
    private Collection<T> result;

    public static <T> PageCommonResult<T> build(Collection<T> data,int count,String msg,boolean isSuccess,int code)
    {
        PageCommonResult<T>temp=new PageCommonResult<T>();
        temp.setResult(data);
        temp.setSuccess(isSuccess);
        temp.setMsg(msg);
        temp.setCode(code) ;
        temp.setCount(count);
        return temp;
    }
}
