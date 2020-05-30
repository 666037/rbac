package com.kad.rbac.dal;
import com.kad.rbac.dal.sqlprovider.SqlUserProvider;
import com.kad.rbac.model.entity.vo.QueryUserVO;
import com.kad.rbac.model.entity.vo.UserVO;
import com.kad.rbac.util.SqlGenerateFactory;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.kad.rbac.model.entity.po.UserDO;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @SelectProvider(type = SqlUserProvider.class,method = "SelectUserList")
    List<UserVO> getUserList(QueryUserVO query);

    @SelectProvider(type = SqlUserProvider.class,method = "SelectUserListCount")
    Integer getUserListCount(QueryUserVO query);

    @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
      int AddList(List<UserDO>list);

    @Select("SELECT * FROM rbac_user where username= #{username}")
    UserDO getUserByUsername(String username);
}
