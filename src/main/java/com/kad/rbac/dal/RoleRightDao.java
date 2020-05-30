package com.kad.rbac.dal;


import com.kad.rbac.model.entity.po.RoleDO;
import com.kad.rbac.model.entity.po.RoleRightDO;
import com.kad.rbac.util.SqlGenerateFactory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RoleRightDao {


    @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
    public int AddRoleRightList(List<RoleRightDO> list);
    @Delete("DELETE FROM `rbac`.`rbac_role_right` WHERE (`role_id` = #{roleId}); ")
    public int DeleteRoleRightList(Integer roleId);

}
