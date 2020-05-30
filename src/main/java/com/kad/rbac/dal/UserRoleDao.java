package com.kad.rbac.dal;

import com.kad.rbac.model.entity.po.OrganiseRoleDO;
import com.kad.rbac.model.entity.po.UserRoleDO;
import com.kad.rbac.util.SqlGenerateFactory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserRoleDao {

    @Select("SELECT * FROM rbac.rbac_organise_role where role_id = #{roleId} ")
    public List<UserRoleDO> getUserRoleList (Integer roleId);

    @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
    public int AddUserRoleList(List<UserRoleDO> list);
    @Delete("DELETE FROM `rbac`.`rbac_user_role` WHERE (`username` = #{username})")
    public int DeleteUserRoleList(String username);



}
