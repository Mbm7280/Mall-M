package com.ex.mall.controller;

import com.ex.mall.base.CommonResult;
import com.ex.mall.base.anno.AspectAnno;
import com.ex.mall.dto.other.PageReqDTO;
import com.ex.mall.model.CmsSubject;
import com.ex.mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品专题
 * // TODO 统一返回结果优化
 */
@Api(tags = "CmsSubjectController",description = "商品专题管理")
@RestController
@RequestMapping("/cms/subject")
public class CmsSubjectController {

    @Autowired
    private CmsSubjectService cmsSubjectService;

    @AspectAnno(desc = "查询所有专题列表")
    @ApiOperation("查询所有专题列表")
    @GetMapping("/queryAllSubjectList")
    public CommonResult<List<CmsSubject>> queryAllSubjectList(){
        List<CmsSubject> cmsSubjectList = cmsSubjectService.queryAllSubjectList();
        return CommonResult.success(cmsSubjectList);
    }

    @AspectAnno(desc = "分页展示专题列表")
    @ApiOperation("分页展示专题列表")
    @PostMapping("/queryPageSubjectlist")
    public CommonResult<List<CmsSubject>> queryPageSubjectlist(@RequestBody PageReqDTO params){
        List<CmsSubject> cmsSubjectList = cmsSubjectService.queryPageSubjectlist(params);
        return CommonResult.success(cmsSubjectList);
    }


}
