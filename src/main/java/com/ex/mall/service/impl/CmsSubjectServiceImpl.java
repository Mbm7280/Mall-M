package com.ex.mall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ex.mall.dto.other.PageReqDTO;
import com.ex.mall.mapper.CmsSubjectMapper;
import com.ex.mall.model.CmsSubject;
import com.ex.mall.model.CmsSubjectExample;
import com.ex.mall.service.CmsSubjectService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品专题
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {

    @Autowired
    private CmsSubjectMapper cmsSubjectMapper;

    /**
     * 查询所有专题列表
     * @return
     */
    @Override
    public List<CmsSubject> queryAllSubjectList() {
        List<CmsSubject> cmsSubjectList = new ArrayList<>();
        cmsSubjectList = cmsSubjectMapper.selectByExample(new CmsSubjectExample());
        if(CollectionUtil.isNotEmpty(cmsSubjectList)){
            return cmsSubjectList;
        }
        return cmsSubjectList;
    }

    /**
     * 分页展示专题列表
     * @return
     */
    @Override
    public List<CmsSubject> queryPageSubjectlist(PageReqDTO params) {
        List<CmsSubject> cmsSubjectList = new ArrayList<>();
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        CmsSubjectExample example = new CmsSubjectExample();
        if(StringUtils.isNotEmpty(params.getKeyword())){
            example.createCriteria().andTitleLike("%" + params.getKeyword() + "%");
            cmsSubjectList = cmsSubjectMapper.selectByExample(example);
            return cmsSubjectList;
        }
        return cmsSubjectList;
    }
}
