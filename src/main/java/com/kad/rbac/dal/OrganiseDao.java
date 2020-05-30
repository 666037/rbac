package com.kad.rbac.dal;


import com.kad.rbac.dal.sqlprovider.SqlOrganiseProvider;
import com.kad.rbac.model.entity.po.CompanyDO;
import com.kad.rbac.model.entity.po.OrganiseDO;
import com.kad.rbac.model.entity.vo.CompanyVO;
import com.kad.rbac.model.entity.vo.OrganiseVO;
import com.kad.rbac.model.entity.vo.QueryOrganiseVO;
import com.kad.rbac.util.SqlGenerateFactory;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrganiseDao {


    @SelectProvider(type = SqlOrganiseProvider.class,method = "SelectOrganise")
    public List<OrganiseVO> GetOrganiseList(QueryOrganiseVO v);

    @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
    public int AddTargetList(List<OrganiseDO>list);

}
