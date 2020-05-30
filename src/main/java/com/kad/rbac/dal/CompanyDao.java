package com.kad.rbac.dal;

import com.kad.rbac.model.entity.po.CompanyDO;
import com.kad.rbac.model.entity.vo.CompanyVO;
import com.kad.rbac.util.SqlGenerateFactory;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CompanyDao {

    @Select("select *,cmp_id as id,cmp_pid as pid from rbac_company ")
    public List<CompanyVO> GetCompanyList();

    @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
    public int AddCompanyList(List<CompanyDO>list);
}
