package com.ex.mall.service;

import com.ex.mall.base.CommonResult;

/**
* @Package: com.ex.mall.service
* @ClassName: UmsMemberService
* @Description: service
 *              -- 会员登录注册管理
* @Author: mbm
* @date: 2020/6/29 22:17
* @Version: 1.0
*/
public interface UmsMemberService {

    /**
     * @author mbm X
     * @methodname : generateAuthCode
     * @description : 生成随机验证码
     * @param telephone :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/29 22:18
     */
    CommonResult generateAuthCode(String telephone);


    /**
     * @author mbm X
     * @methodname : verifyAuthCode
     * @description : 校验验证码
     * @param telephone :
     * @param authCode :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/29 22:18
     */
    CommonResult verifyAuthCode(String telephone,String authCode);

}
