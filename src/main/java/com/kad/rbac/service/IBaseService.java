package com.kad.rbac.service;

import com.kad.rbac.model.common.CommonResult;
import com.kad.rbac.model.common.GenericCommonResult;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IBaseService<T> {

    public int AddTargetList(List<T>list);


}
