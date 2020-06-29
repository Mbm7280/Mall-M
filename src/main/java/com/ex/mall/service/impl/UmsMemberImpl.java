package com.ex.mall.service.impl;

import com.ex.mall.base.CommonResult;
import com.ex.mall.service.RedisService;
import com.ex.mall.service.UmsMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
* @Package: com.ex.mall.service.impl
* @ClassName: UmsServiceImpl
* @Description: impl
 *              -- 会员登录注册管理
* @Author: mbm
* @date: 2020/6/29 22:19
* @Version: 1.0
*/
@Service
public class UmsMemberImpl implements UmsMemberService {

    @Autowired
    RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    /**
     * @Author: mbm
     * @ClassName: UmsServiceImpl
     * @MethodName: generateAuthCode
     * @Param:  [telephone]
     * @return: com.ex.mall.base.CommonResult
     * @Description: 生成随机验证码
     * @date: 2020/6/29 22:29
     * @Version: 1.0
     */
    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i < 6;i++){
            sb.append(random.nextInt(10)); // 生成随机的一个整数，范围是0-62
        }
        // 验证手机号码并且存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    /**
     * @Author: mbm
     * @ClassName: UmsServiceImpl
     * @MethodName: verifyAuthCode
     * @Param:  [telephone, authCode]
     * @return: com.ex.mall.base.CommonResult
     * @Description: 校验验证码
     * @date: 2020/6/29 22:33
     * @Version: 1.0
     */
    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {

        if(StringUtils.isEmpty(telephone) && StringUtils.isEmpty(authCode)){
            return CommonResult.failed("请输入验证码");
        }
        String redisAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if (redisAuthCode.equals(authCode)) {
            return CommonResult.success("验证码校验成功");
        }else{
            return CommonResult.failed("验证码不正确");
        }
    }
}
