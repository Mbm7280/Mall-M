package com.ex.mall.controller;

import com.ex.mall.base.CommonResult;
import com.ex.mall.dto.res.UmsAdminLoginReqDTO;
import com.ex.mall.model.UmsAdmin;
import com.ex.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
* @Package: com.ex.mall.controller
* @ClassName: UmsAdminController
* @Description: 权限管理
* @Author: mbm
* @date: 2020/6/27 16:48
* @Version: 1.0
*/
@RequestMapping("/admin")
@Controller
@Api(tags = "UmsAdminController",description = "权限管理")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * @author mbm X
     * @methodname : register
     * @description : 注册
     * @param umsAdminParam :
     * @param result :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 16:51
     */
    @PostMapping("/register")
    @ApiOperation("注册")
    @ResponseBody
    public CommonResult register(@RequestBody UmsAdmin umsAdminParam, BindingResult result){
        return adminService.register(umsAdminParam);
    }

    /**
     * @author mbm X
     * @methodname : login
     * @description : 登录
     * @param umsAdminLoginReqDTO :
     * @param result :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 16:54
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginReqDTO umsAdminLoginReqDTO,BindingResult result){
        String token = adminService.login(umsAdminLoginReqDTO.getUsername(), umsAdminLoginReqDTO.getPassword());
        if(StringUtils.isNotEmpty(token)){
            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("token",token);
            tokenMap.put("tokenHead",tokenHead);
            return CommonResult.success(tokenMap);
        }
        return CommonResult.failed();
    }

    /**
     * @author mbm X
     * @methodname : getPerMissionList
     * @description : 获取所有权限
     * @param adminId :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 16:59
     */
    @GetMapping("/getPerMissionList")
    @ApiOperation("获取所有权限")
    @ResponseBody
    public CommonResult getPerMissionList(@RequestBody Long adminId){
        return adminService.getPerMissionList(adminId);
    }


}
