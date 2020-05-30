package com.kad.rbac.dal.sqlprovider;

import java.util.List;

public class SqlRightProvider {
    
    public  String SelectMenuList(List<Integer> roles)
    {
        
        String sqlWhere="  where  1=1";
        String sqlAnd="";
        if(null!=roles&&roles.size()>0)
        {
            sqlAnd+=   "  and role_rights.role_id in( ";

            for (Integer o :roles) {

                sqlAnd+=  o+",";
            }
            sqlAnd= sqlAnd.substring(0,sqlAnd.lastIndexOf(","));
            sqlAnd+=   " ) ";
        }
        String sql="select distinct rights.*,rights.right_id as id,rights.right_pid as pid  from rbac.rbac_right rights         " +
                "inner join rbac.rbac_role_right role_rights         " +
                "on rights.right_id = role_rights.right_id         " +
                sqlWhere+
                sqlAnd;
        return  sql;
        
    }
    
}
