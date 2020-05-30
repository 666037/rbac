package com.kad.rbac.security.dal;
import com.kad.rbac.security.model.AuthUserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthUserDal {

    @Select("SELECT roles.role_id,roles.role_name,users.username,users.password FROM rbac.rbac_user_role user_role     " +
            "inner join rbac_user users      " +
            "on users.username=user_role.username     " +
            "inner join rbac_role roles     " +
            "on user_role.role_id = roles.role_id     " +
            "where user_role.username=#{UserName}")
    List<AuthUserDetail> loadUserByUsername(String UserName);

    @Select("SELECT role_rights.role_id FROM rbac.rbac_right rights " +
            "inner join rbac.rbac_role_right role_rights " +
            "on rights.right_id=role_rights.right_id " +
            " where lower(rights.right_url)  = #{Url}")
    List<String> loadRoleIdsByUrl(String Url);
}
