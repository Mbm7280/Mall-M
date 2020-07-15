package com.ex.mall.controller;

import com.ex.mall.base.CommonResult;
import com.ex.mall.base.anno.AspectAnno;
import com.ex.mall.model.PmsBrand;
import com.ex.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
* @Package: com.ex.mall.controller
* @ClassName: PmsBrandController
* @Description: Description
* @Author: mbm
* @date: 2020/6/27 10:40
* @Version: 1.0
*/
@Api(tags = "PmsBrandController",description = "品牌管理")
@RequestMapping("/brand")
@Controller
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    /**
     * @author mbm X
     * @methodname : selectAllBrands
     * @description : 查询品牌列表
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 10:45
     */
    @AspectAnno(desc = "查询品牌列表")
    @ApiOperation(value = "查询品牌列表")
    @PreAuthorize("hasAnyAuthority('pms:brand:read')")
    @GetMapping("/all")
    @ResponseBody
    public CommonResult selectAllBrands(){
        return pmsBrandService.selectAllBrands();
    }

    /**
     * @author mbm X
     * @methodname : insertBrand
     * @description : 新增品牌
     * @param pmsBrand :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 10:46
     */
    @ApiOperation(value = "新增品牌")
    @PreAuthorize("hasAnyAuthority('pms:brand:create')")
    @PostMapping("/ins")
    @ResponseBody
    public CommonResult insertBrand(@RequestBody PmsBrand pmsBrand){
        return pmsBrandService.insertBrand(pmsBrand);
    }

    /**
     * @author mbm X
     * @methodname : delBrand
     * @description : 删除品牌
     * @param id :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 10:46
     */
    @ApiOperation(value = "删除品牌")
    @PreAuthorize("hasAnyAuthority('pms:brand:delete')")
    @PostMapping("/del")
    @ResponseBody
    public CommonResult delBrand(Long id){
        return pmsBrandService.delBrand(id);
    }

    /**
     * @author mbm X
     * @methodname : upBrand
     * @description : 更新品牌
     * @param pmsBrand :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 10:46
     */
    @ApiOperation(value = "更新品牌" )
    @PreAuthorize("hasAnyAuthority('pms:brand:update')")
    @PostMapping("/up")
    @ResponseBody
    public CommonResult upBrand(@RequestBody PmsBrand pmsBrand){
        return pmsBrandService.upBrand(pmsBrand);
    }


}
