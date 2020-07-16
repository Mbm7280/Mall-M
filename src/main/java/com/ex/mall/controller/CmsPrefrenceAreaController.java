package com.ex.mall.controller;

import com.ex.mall.base.CommonResult;
import com.ex.mall.base.anno.AspectAnno;
import com.ex.mall.model.CmsPrefrenceArea;
import com.ex.mall.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 优先商品管理
 */
@Api(tags = "CmsPrefrenceAreaController",description = "优先商品管理")
@RestController
@RequestMapping("/cms/PrefrenceArea")
public class CmsPrefrenceAreaController {

    @Autowired
    CmsPrefrenceAreaService cmsPrefrenceAreaService;

    /**
     * 查询所有优先商品列表
     * @return
     */
    @AspectAnno(desc = "查询所有优先商品列表")
    @ApiOperation("查询所有优先商品列表")
    @PostMapping("/queryAllPrefrence")
    public CommonResult<List<CmsPrefrenceArea>> queryAllPrefrence(){
        List<CmsPrefrenceArea> cmsPrefrenceAreaList = cmsPrefrenceAreaService.queryAllPrefrence();
        return CommonResult.success(cmsPrefrenceAreaList);
    }

}
