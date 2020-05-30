package com.kad.rbac.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@Data
public class Right implements GrantedAuthority {


    private  Integer roleId;

    private String roleName;
    @Override
    public String getAuthority() {
        return roleId.toString();
    }
}
