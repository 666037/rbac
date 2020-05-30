package com.kad.rbac.dal;

import com.kad.rbac.dal.sqlprovider.SqlRoleProvider;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.po.RoleDO;
import com.kad.rbac.model.entity.vo.QueryRoleVO;
import com.kad.rbac.model.entity.vo.RoleVO;
import com.kad.rbac.util.SqlGenerateFactory;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDao {

    @SelectProvider(type = SqlRoleProvider.class,method = "SelectRoleList")
    List<RoleVO> getRoleList(QueryRoleVO query);

    @SelectProvider(type = SqlRoleProvider.class,method = "SelectRoleListCount")
    Integer getRoleListCount(QueryRoleVO query);

    @Select("select right_id from rbac.rbac_role_right where role_id = #{roleId}")
    public List<Integer> getRoleRightIdList(String roleId);

    @Select("select max(role_id) from rbac.rbac_role")
    public  Integer getMaxRoleId();
    @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
    public int AddRoleList(List<RoleDO>list);


}
