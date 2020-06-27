package com.ex.mall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ex.mall.base.CommonResult;
import com.ex.mall.mapper.PmsBrandMapper;
import com.ex.mall.model.PmsBrand;
import com.ex.mall.model.PmsBrandExample;
import com.ex.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**PmsBrandMapper.xml
* @Package: com.ex.mall.service.impl
* @ClassName: PmsBrandServiceImpl
* @Description: serivceImpl-品牌管理
* @Author: mbm
* @date: 2020/6/26 22:31
* @Version: 1.0
*/
@Service
public class PmsBrandServiceImpl implements PmsBrandService{

    @Autowired
    PmsBrandMapper pmsBrandMapper;

    /**
     * @author mbm X
     * @methodname : selectAllBrands
     * @description : 查询品牌列表
     * @return : java.util.List<com.ex.mall.model.PmsBrand>
     * @date : 2020/6/27 10:25
     */
    @Override
    public CommonResult selectAllBrands() {
        List<PmsBrand> pmsBrands = pmsBrandMapper.selectByExample(new PmsBrandExample());
        if(CollectionUtil.isNotEmpty(pmsBrands)){
            return CommonResult.success(pmsBrands);
        }
        return CommonResult.failed();
    }

    /**
     * @author mbm X
     * @methodname : insertBrand
     * @description : 新增品牌
     * @param pmsBrand :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 10:35
     */
    @Override
    public CommonResult insertBrand(PmsBrand pmsBrand) {
        if(null != pmsBrand){
            int i = pmsBrandMapper.insertSelective(pmsBrand);
            if(i == 1){
                return CommonResult.success();
            }
        }
        return CommonResult.failed();
    }

    /**
     * @author mbm X
     * @methodname : delBrand
     * @description : 删除品牌
     * @param id :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 10:35
     */
    @Override
    public CommonResult delBrand(Long id) {
       if(null != id){
           int i = pmsBrandMapper.deleteByPrimaryKey(id);
           if(i == 1){
               return CommonResult.success();
           }
       }
        return CommonResult.failed();
    }

    /**
     * @author mbm X
     * @methodname : upBrand
     * @description : 更新品牌
     * @param pmsBrand :
     * @return : com.ex.mall.base.CommonResult
     * @date : 2020/6/27 10:34
     */
    @Override
    public CommonResult upBrand(PmsBrand pmsBrand) {
        if(null != pmsBrand){
            int i = pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
            if(i ==  1){
                return CommonResult.success();
            }
        }
        return CommonResult.failed();
    }


}
