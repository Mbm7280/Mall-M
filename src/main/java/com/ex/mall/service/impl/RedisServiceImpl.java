package com.ex.mall.service.impl;

import com.ex.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
* @Package: com.ex.mall.service.impl
* @ClassName: RedisServiceImpl
* @Description: impl
 *              -- redis操作实现类
* @Author: mbm
* @date: 2020/6/29 22:07
* @Version: 1.0
*/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @Author: mbm
     * @ClassName: RedisServiceImpl
     * @MethodName: set
     * @Param:  [key, value]
     * @return: void
     * @Description: set--操作
     * @date: 2020/6/29 22:12
     * @Version: 1.0
     */
    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * @Author: mbm
     * @ClassName: get--操作
     * @MethodName: get
     * @Param:  [key]
     * @return: java.lang.String
     * @Description: Description
     * @date: 2020/6/29 22:13
     * @Version: 1.0
     */
    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Author: mbm
     * @ClassName: RedisServiceImpl
     * @MethodName: expire
     * @Param:  [key, expire]
     * @return: boolean
     * @Description: expire--操作
     * @date: 2020/6/29 22:13
     * @Version: 1.0
     */
    @Override
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key,expire, TimeUnit.SECONDS);
    }

    /**
     * @Author: mbm
     * @ClassName: RedisServiceImpl
     * @MethodName: remove
     * @Param:  [key]
     * @return: void
     * @Description: del-操作
     * @date: 2020/6/29 22:14
     * @Version: 1.0
     */
    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @Author: mbm
     * @ClassName: RedisServiceImpl
     * @MethodName: increment
     * @Param:  [key, delta]
     * @return: java.lang.Long
     * @Description: increment--操作
     * @date: 2020/6/29 22:14
     * @Version: 1.0
     */
    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
}
