package com.kad.rbac.dal.sqlprovider;

import com.kad.rbac.enums.SqlOperateSymbol;
import com.kad.rbac.enums.SqlRelateSymbol;
import com.kad.rbac.model.entity.vo.QueryRoleVO;

public class SqlRoleProvider extends SqlBaseProvider{


    public  String SelectRoleList(QueryRoleVO vo)
    {
        super.sqlWhere=new StringBuilder();
        appendWhereSql("cmp.cmp_id",vo.getCmpId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("org.organise_id",vo.getOrganiseId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("or_role.role_id",vo.getRoleId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("role.role_name",vo.getRoleName(), SqlRelateSymbol.AND, SqlOperateSymbol.LIKE);
        return  super.SelectPageSql(vo,
                "SELECT role.*,org.organise_name,cmp.cmp_name,org.organise_id,cmp.cmp_id  FROM  rbac_organise_role or_role     ",
                getSelectBody());

    }
    public  String SelectRoleListCount(QueryRoleVO vo)
    {
        super.sqlWhere=new StringBuilder();
        appendWhereSql("cmp.cmp_id",vo.getCmpId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("org.organise_id",vo.getOrganiseId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("or_role.role_id",vo.getRoleId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("role.role_name",vo.getRoleName(), SqlRelateSymbol.AND, SqlOperateSymbol.LIKE);
       return super.SelectPageSql(vo,
            "SELECT COUNT(1)  FROM  rbac_organise_role or_role     ",
            getSelectBody());

    }


    private String getSelectBody()
    {
        return  "inner join rbac_organise org     " +
                "on or_role.organise_id =org.organise_id    " +
                "inner join rbac_company cmp     " +
                "on org.cmp_id =cmp.cmp_id    " +
                "inner join rbac_role role     " +
                "on or_role.role_id = role.role_id    " ;
    }
}
