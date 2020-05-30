package com.kad.rbac.dal.sqlprovider;

import com.kad.rbac.model.entity.vo.QueryOrganiseVO;
import org.springframework.util.StringUtils;

public class SqlOrganiseProvider {


    public static String SelectOrganise(QueryOrganiseVO vo)
    {
        String sql="SELECT c.cmp_name,o.*,o.organise_id as id,o.organise_pid as pid FROM rbac.rbac_organise o  " +
                "inner join rbac.rbac_company c  " +
                "on o.cmp_id = c.cmp_id  ";
          if(null!=vo.getCmpId()&&!vo.getCmpId().isEmpty())
          {
              sql+=" where c.cmp_id ="+vo.getCmpId();
          }
          return  sql;
    }
}
