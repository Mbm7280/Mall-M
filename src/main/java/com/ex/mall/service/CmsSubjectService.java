package com.ex.mall.service;

import com.ex.mall.dto.other.PageReqDTO;
import com.ex.mall.model.CmsSubject;

import java.util.List;

/**
 * 商品专题
 */
public interface CmsSubjectService {

    /**
     * 查询所有专题
     * @return
     */
    List<CmsSubject> queryAllSubjectList();

    /**
     * 分页展示专题列表
     * @return
     */
    List<CmsSubject> queryPageSubjectlist(PageReqDTO params);


}
