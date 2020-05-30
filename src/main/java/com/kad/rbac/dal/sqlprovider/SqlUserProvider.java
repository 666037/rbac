package com.kad.rbac.dal.sqlprovider;

import com.kad.rbac.enums.SqlOperateSymbol;
import com.kad.rbac.enums.SqlRelateSymbol;
import com.kad.rbac.model.entity.vo.QueryUserVO;

public class SqlUserProvider extends SqlBaseProvider {

     public String SelectUserList(QueryUserVO queryUserVO)
     {
         appendWhereSql("users.realname",queryUserVO.getRealname(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
         appendWhereSql("users.username",queryUserVO.getUsername(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
         appendWhereSql("org.cmp_id",queryUserVO.getCmpId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
         appendWhereSql("users.organise_id",queryUserVO.getOrganiseId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
         appendWhereSql("users.sex",queryUserVO.getSex(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
       return  super.SelectPageSql(queryUserVO,
                 "SELECT users.*,org.cmp_id FROM rbac.rbac_user as users ",
               getSelectBody() );

     }
    public String SelectUserListCount(QueryUserVO queryUserVO)
    {
        appendWhereSql("users.realname",queryUserVO.getRealname(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("users.username",queryUserVO.getUsername(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("org.cmp_id",queryUserVO.getCmpId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("users.organise_id",queryUserVO.getOrganiseId(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        appendWhereSql("users.sex",queryUserVO.getSex(), SqlRelateSymbol.AND, SqlOperateSymbol.EQUAL);
        return  super.SelectPageSql(queryUserVO,
                "SELECT count(1) FROM rbac.rbac_user as users ",
                getSelectBody() );

    }

    private  String getSelectBody()
    {

        return "left join rbac.rbac_organise org " +
                "on org.organise_id = users.organise_id  ";
    }
}
