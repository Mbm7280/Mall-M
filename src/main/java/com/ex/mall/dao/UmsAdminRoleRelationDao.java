package com.ex.mall.dao;

import com.ex.mall.model.UmsPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Package: com.ex.mall.dao
* @ClassName: UmsAdminRoleRelationDao
* @Description: 后台权限管理
* @Author: mbm
* @date: 2020/6/27 15:23
* @Version: 1.0
*/
@Mapper
public interface UmsAdminRoleRelationDao {

    /**
     * 获取该用户的所有权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

}
