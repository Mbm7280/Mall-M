package com.ex.mall.service;

import com.ex.mall.base.CommonResult;
import com.ex.mall.model.PmsBrand;


/**
* @Package: com.ex.mall.service
* @ClassName: PmsBrandService
* @Description: service-品牌管理
* @Author: mbm
* @date: 2020/6/26 22:31
* @Version: 1.0
*/
public interface PmsBrandService{

    /**
     * 查询品牌列表
     * @return
     */
    CommonResult selectAllBrands();

    /**
     * 新增品牌
     * @return
     */
    CommonResult insertBrand(PmsBrand pmsBrand);

    /**
     * 删除品牌
     * @return
     */
    CommonResult delBrand(Long id);

    /**
     * 更新品牌
     * @return
     */
    CommonResult upBrand(PmsBrand pmsBrand);

}
