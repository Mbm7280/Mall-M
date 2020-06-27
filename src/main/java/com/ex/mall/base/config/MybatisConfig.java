package com.ex.mall.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
* @Package: com.ex.mall.base.config
* @ClassName: MybatisConfig
* @Description: 配置类
 *              -- Config Mybatis
* @Author: mbm
* @date: 2020/6/26 22:28
* @Version: 1.0
*/
@Configuration
@MapperScan({"com.ex.mall.mapper","com.ex.mall.dao"})
public class MybatisConfig {
}
