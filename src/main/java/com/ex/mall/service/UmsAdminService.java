package com.ex.mall.service;

import com.ex.mall.base.CommonResult;
import com.ex.mall.model.UmsAdmin;

/**
* @Package: com.ex.mall.service
* @ClassName: UmsAdminService
* @Description: service
 *              -- 后台管理
* @Author: mbm
* @date: 2020/6/27 15:28
* @Version: 1.0
*/
public interface UmsAdminService {

    /**
     * 根据用户名获取该用户信息
     * @param username
     * @return
     */
    CommonResult getAdminByUsername(String username);

    /**
     * 注册
     * @param umsAdmin
     * @return
     */
    CommonResult register(UmsAdmin  umsAdmin);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username,String password);

    /**
     * 获取该用户的所有权限
     * @param adminId
     * @return
     */
    CommonResult getPerMissionList(Long adminId);

}
