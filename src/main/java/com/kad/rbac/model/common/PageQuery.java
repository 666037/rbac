package com.kad.rbac.model.common;

import lombok.Data;

@Data
public class PageQuery extends BaseQuery {

    private   Integer limit;
    private   Integer page;
    private  Integer startIndex;
    public  Integer getStartIndex() {
         if(null!=page&&null!=limit) {
             return page > 0 ? (page - 1) * limit : 0;
         }else
         {
             return  0;
         }
    }
}
