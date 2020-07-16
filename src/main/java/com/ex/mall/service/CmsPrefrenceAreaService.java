package com.ex.mall.service;

import com.ex.mall.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 商品优先管理Controller
 */
public interface CmsPrefrenceAreaService {

    /**
     * 展示所有优先商品
     * @return
     */
    List<CmsPrefrenceArea> queryAllPrefrence();

}
