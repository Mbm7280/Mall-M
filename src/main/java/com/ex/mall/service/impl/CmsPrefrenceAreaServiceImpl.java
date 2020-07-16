package com.ex.mall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ex.mall.base.CommonResult;
import com.ex.mall.mapper.CmsPrefrenceAreaMapper;
import com.ex.mall.model.CmsPrefrenceArea;
import com.ex.mall.model.CmsPrefrenceAreaExample;
import com.ex.mall.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 优先商品
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {


    @Autowired
    private CmsPrefrenceAreaMapper cmsPrefrenceAreaMapper;

    /**
     * 优先商品列表
     * @return
     */
    @Override
    public List<CmsPrefrenceArea> queryAllPrefrence() {
        List<CmsPrefrenceArea> cmsPrefrenceAreaList = new ArrayList<>();
        cmsPrefrenceAreaList = cmsPrefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
        if(CollectionUtil.isNotEmpty(cmsPrefrenceAreaList)){
            return cmsPrefrenceAreaList;
        }
        return cmsPrefrenceAreaList;
    }


}
