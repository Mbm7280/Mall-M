package com.ex.mall.controller;

import com.ex.mall.base.CommonResult;
import com.ex.mall.nosql.document.MemberReadHistory;
import com.ex.mall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Package: com.ex.mall.controller
* @ClassName: MemberReadHistoryController
* @Description: Controller
 *              会员商品浏览记录管理
* @Author: mbm
* @date: 2020/7/11 16:17
* @Version: 1.0
*/
@Api(tags = "MemberReadHistoryController",description = "会员商品浏览记录管理")
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    /**
     * @Author: mbm
     * @ClassName: MemberReadHistoryController
     * @MethodName: createReadHistory
     * @Param:  [memberReadHistory]
     * @return: com.ex.mall.base.CommonResult
     * @Description: 创建浏览记录
     * @date: 2020/7/11 16:21
     * @Version: 1.0
     */
    @ApiOperation("创建浏览记录")
    @PostMapping("/crateReadHistory")
    public CommonResult createReadHistory(@RequestBody MemberReadHistory memberReadHistory){
        int i = memberReadHistoryService.create(memberReadHistory);
        return CommonResult.success(i);
    }

    /**
     * @Author: mbm
     * @ClassName: MemberReadHistoryController
     * @MethodName: batchDelReadHistory
     * @Param:  [ids]
     * @return: com.ex.mall.base.CommonResult
     * @Description: 批量删除浏览记录
     * @date: 2020/7/11 16:23
     * @Version: 1.0
     */
    @ApiOperation("批量删除浏览记录")
    @PostMapping("batchDel")
    public CommonResult batchDelReadHistory(@RequestBody List<String> ids){
        int del = memberReadHistoryService.del(ids);
        return CommonResult.success(del);
    }

    /**
     * @Author: mbm
     * @ClassName: MemberReadHistoryController
     * @MethodName: findReadHistory
     * @Param:  [memberId]
     * @return: com.ex.mall.base.CommonResult
     * @Description: 查询浏览记录
     * @date: 2020/7/11 16:25
     * @Version: 1.0
     */
    @ApiOperation("查询浏览记录")
    @GetMapping("/findReadHistory")
    public CommonResult findReadHistory(@RequestParam Long memberId){
        List<MemberReadHistory> memberReadList = memberReadHistoryService.findMemberReadList(memberId);
        return CommonResult.success(memberReadList);
    }


}
