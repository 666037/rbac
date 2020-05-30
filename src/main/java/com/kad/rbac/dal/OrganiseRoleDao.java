package com.kad.rbac.dal;

import com.kad.rbac.model.entity.po.OrganiseRoleDO;
import com.kad.rbac.util.SqlGenerateFactory;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface OrganiseRoleDao {


    @Select("SELECT * FROM rbac.rbac_organise_role where role_id = #{roleId} ")
    public List<OrganiseRoleDO> getOrganiseRoleList (Integer roleId);
    @Select("SELECT * FROM rbac.rbac_organise_role where organise_id = #{OrganiseId} ")
    public List<OrganiseRoleDO> getOrganiseRoleListByOrganiseId (Integer organiseId);

    @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
    public int AddOrganiseRoleList(List<OrganiseRoleDO> list);


}
