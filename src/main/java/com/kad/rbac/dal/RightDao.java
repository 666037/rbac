package com.kad.rbac.dal;

import com.kad.rbac.dal.sqlprovider.SqlRightProvider;
import com.kad.rbac.model.entity.po.RightDO;
import com.kad.rbac.model.entity.vo.RightVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.kad.rbac.util.SqlGenerateFactory;
import java.util.List;

@Mapper
@Repository
public interface RightDao {


   @SelectProvider(type = SqlRightProvider.class,method = "SelectMenuList")
   public List<RightVO> GetMenuList(@Param("roles") List<Integer>roles);


   @Select("select *,rights.right_id as id,rights.right_pid as pid from rbac.rbac_right rights")
   public List<RightVO> GetRightList();


   @InsertProvider(type = SqlGenerateFactory.class,method = "insertOrUpdate")
   public int AddRightList(List<RightDO>list);






}
