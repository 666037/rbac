package com.kad.rbac.model.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class TreeVO implements Serializable {

    public  Integer id;

    public  Integer pid;

    public Collection<?extends TreeVO> children;
}
