package com.kad.rbac.enums;

import lombok.Getter;

/**
 * SQL关系运算符
 */
@Getter
public enum SqlRelateSymbol {
    AND(" AND "),
    OR(" OR "),
    IN(" IN "),
    ORDER_BY(" ORDER BY "),
    ;




    private String value;

    SqlRelateSymbol(String value){
        this.value = value;

    }

}