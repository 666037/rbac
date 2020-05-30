package com.kad.rbac.dal.sqlprovider;

import com.kad.rbac.enums.SqlOperateSymbol;
import com.kad.rbac.enums.SqlRelateSymbol;
import com.kad.rbac.model.common.PageQuery;

public class SqlBaseProvider {

    public   StringBuilder sqlWhere=new StringBuilder();
    public String SelectSql(PageQuery vo, String selectParam,String selectBody)
    {

        String where="where 1=1";
        String sql=" " +
                selectParam+
                selectBody+
                where +
                sqlWhere
                 ;
        return  sql;
    }
    public String SelectPageSql(PageQuery vo, String selectParam,String selectBody)
    {

        String where="where 1=1";
        String limitSql= " ";
        if(null!=vo.getLimit()&&null!=vo.getPage())
        {
            limitSql= "  limit %d, %d ";
        }else
        {
            limitSql= "  ";
        }
        String sql=" " +
                selectParam+"  "+
                selectBody+"  "+
                where +"  "+
                sqlWhere+"  "+
                limitSql+"  ";
        if(null!=vo.getLimit()&&null!=vo.getPage())
        {
            sql= String.format(sql, vo.getStartIndex(),vo.getLimit());
        }
        return  sql;
    }

    /**
     * 追加where语句
     * @param sqlFiledName
     * @param filedValue
     * @param relateSymbol
     * @param operate
     */
    public void appendWhereSql(String sqlFiledName, Object filedValue, SqlRelateSymbol relateSymbol, SqlOperateSymbol operate)
    {
        if (filedValue != null && !filedValue.toString().isEmpty()) {

               sqlWhere.append(
                      relateSymbol.getValue()
                      +sqlFiledName+operate.getValue()
                      +"'"
                      +(operate.getValue().equals(SqlOperateSymbol.LEFT_LIKE.getValue())?"%":"")
                      +(operate.getValue().equals(SqlOperateSymbol.LIKE.getValue())?"%":"")
                      +filedValue
                       +(operate.getValue().equals(SqlOperateSymbol.LIKE.getValue())?"%":"")
                      +(operate.getValue().equals(SqlOperateSymbol.RIGHT_LIKE.getValue())?"%":"")
                       +"'"
              );
        }
    }




}
