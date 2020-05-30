package com.kad.rbac.model.entity.po;

import com.kad.rbac.util.SqlAnnotationTableName;
import lombok.Data;

import java.util.Date;
@Data
@SqlAnnotationTableName(value = "rbac.rbac_user")
public class UserDO extends BaseDO {
    private Integer organiseId;
    private String username;
    private String password;
    private String salt;
    private String avatarUrl;
    private String realname;
    private Integer jobnumber;
    private String post;
    private Integer sex;
    private Date birthday;
    private Integer province;
    private Integer city;
    private Integer area;
    private String address;
    private String phone;
    private String tel;
    private String email;
    private String qq;
    //用户状态 0 正常 1禁用
    private Integer status;

}
