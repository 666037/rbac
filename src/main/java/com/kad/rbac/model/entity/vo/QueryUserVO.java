package com.kad.rbac.model.entity.vo;

import com.kad.rbac.model.common.PageQuery;
import lombok.Data;

@Data
public class QueryUserVO extends PageQuery {
   private Integer cmpId;
    private Integer organiseId;
    private String username;
    private String realname;
    private Integer  sex;
}
