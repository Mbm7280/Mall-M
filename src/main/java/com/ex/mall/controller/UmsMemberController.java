package com.ex.mall.controller;

import com.ex.mall.base.CommonResult;
import com.ex.mall.dto.res.UmsMemberReqDTO;
import com.ex.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
* @Package: com.ex.mall.controller
* @ClassName: UmsMemberController
* @Description: controller
 *              -- UmsMember
* @Author: mbm
* @date: 2020/6/29 22:15
* @Version: 1.0
*/
@Controller
@Api(tags = "UmsMemberController",description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    UmsMemberService memberService;

    /**
     * @Author: mbm
     * @ClassName: UmsMemberController
     * @MethodName: generateAuthCode
     * @Param:  [telephone]
     * @return: com.ex.mall.base.CommonResult
     * @Description: 生成随机验证码
     * @date: 2020/6/29 22:35
     * @Version: 1.0
     */
    @ApiOperation("获取验证码")
    @PreAuthorize("hasAnyAuthority('ums:generateAuthCode')")
    @PostMapping("/generateAuthCode")
    @ResponseBody
    public CommonResult generateAuthCode(@RequestBody UmsMemberReqDTO umsMemberReqDTO){
        return memberService.generateAuthCode(umsMemberReqDTO.getTelephone());
    }

    /**
     * @Author: mbm
     * @ClassName: UmsMemberController
     * @MethodName: verifyAuthCode
     * @Param:  [telephone, authCode]
     * @return: com.ex.mall.base.CommonResult
     * @Description: 校验验证码
     * @date: 2020/6/29 22:36
     * @Version: 1.0
     */
    @ApiOperation("校验验证码")
    @PreAuthorize("hasAnyAuthority('ums:verifyAuthCode')")
    @PostMapping("/verifyAuthCode")
    @ResponseBody
    public CommonResult verifyAuthCode(@RequestBody UmsMemberReqDTO umsMemberReqDTO){
        return memberService.verifyAuthCode(umsMemberReqDTO.getTelephone(), umsMemberReqDTO.getAuthCode());
    }

}
