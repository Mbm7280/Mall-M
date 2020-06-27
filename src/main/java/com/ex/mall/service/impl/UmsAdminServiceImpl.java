package com.ex.mall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ex.mall.base.CommonResult;
import com.ex.mall.base.utils.JwtTokenUtil;
import com.ex.mall.dao.UmsAdminRoleRelationDao;
import com.ex.mall.mapper.UmsAdminMapper;
import com.ex.mall.model.UmsAdmin;
import com.ex.mall.model.UmsAdminExample;
import com.ex.mall.model.UmsPermission;
import com.ex.mall.service.UmsAdminService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    /**
     * @author mbm X
     * @methodname : 根据姓名获取用户信息
     * @description : description
     * @param username :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 15:39
     */
    @Override
    public CommonResult getAdminByUsername(String username) {
        try{
            logger.info("根据姓名获取用户信息 getAdminByUsername:{}",username);
            if(StringUtils.isNotEmpty(username)){
                UmsAdminExample example = new UmsAdminExample();
                example.createCriteria().andUsernameEqualTo(username).andStatusEqualTo(1);
                List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
                if(CollectionUtil.isNotEmpty(umsAdmins)){
                    UmsAdmin admin = umsAdmins.get(0);
                    logger.info("根据姓名获取用户信息 getAdminByUsername:{}",umsAdmins);
                    return CommonResult.success(admin);
                }
            }
        }catch (Exception e){
            logger.error("根据姓名获取用户信息异常 getAdminByUsername:{}",e.getMessage());
        }
        return CommonResult.failed();
    }

    /**
     * @author mbm X
     * @methodname : register
     * @description : 注册
     * @param umsAdminParam :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 15:50
     */
    @Override
    public CommonResult register(UmsAdmin umsAdminParam) {
        logger.info("注册 register 参数:{}",umsAdminParam);
       try{
           UmsAdmin umsAdmin = new UmsAdmin();
           BeanUtils.copyProperties(umsAdminParam,umsAdmin);
           umsAdmin.setCreateTime(new Date());
           umsAdmin.setStatus(1);
           // 查询是否用户已存在
           UmsAdminExample example = new UmsAdminExample();
           example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
           List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
           if(CollectionUtil.isEmpty(umsAdmins)){
               String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
               umsAdmin.setPassword(encodePassword);
               int insert = adminMapper.insert(umsAdmin);
               if(insert == 1){
                   return CommonResult.success();
               }
           }
       }catch (Exception e){
           logger.error("注册异常 register :{}",e.getMessage());
       }
        return CommonResult.failed();
    }

    /**
     * @author mbm X
     * @methodname : login
     * @description : 登录
     * @param username :
     * @param password :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 16:00
     */
    @Override
    public String login(String username, String password) {
        logger.error("登录参数 login :{}",username,password);
        String token = null;
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                return null;
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            if(StringUtils.isEmpty(token)){
                return null;
            }
            return token;
        }catch (Exception e){
            logger.error("登录异常 login :{}",e.getMessage());
        }
        return null;
    }

    /**
     * @author mbm X
     * @methodname : getPerMissionList
     * @description : 获取所有参数
     * @param adminId :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 16:02
     */
    @Override
    public CommonResult getPerMissionList(Long adminId) {
        logger.error("获取权限请求参数 getPerMissionList:{}",adminId);
        try {
            if(null != adminId){
                List<UmsPermission> permissionList = adminRoleRelationDao.getPermissionList(adminId);
                if(CollectionUtil.isNotEmpty(permissionList)){
                    System.out.println(permissionList);
                    return CommonResult.success(permissionList);
                }
            }
        }catch (Exception e){
            logger.error("获取权限异常 getPerMissionList:{}",e.getMessage());
        }
        return CommonResult.failed();
    }
}
