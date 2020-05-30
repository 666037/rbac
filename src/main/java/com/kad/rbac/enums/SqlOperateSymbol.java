package com.kad.rbac.enums;

import lombok.Getter;

@Getter
/**
 * SQL 关系操作符
 */
public enum SqlOperateSymbol {
    EQUAL(" = "),
    UNEQUAL(" <> "),
    MORE_EQUAL(" >= "),
    LESS_EQUAL(" <= "),
    LEFT_LIKE(" LIKE "),
    RIGHT_LIKE(" LIKE "),
    LIKE(" LIKE "),;




    private String value;

    SqlOperateSymbol(String value){
        this.value = value;

    }

}

