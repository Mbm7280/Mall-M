package com.ex.mall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ex.mall.base.CommonResult;
import com.ex.mall.dto.req.PmsCommentReqDTO;
import com.ex.mall.mapper.PmsCommentMapper;
import com.ex.mall.model.PmsComment;
import com.ex.mall.model.PmsCommentExample;
import com.ex.mall.service.PmsCommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 商品评论
 * TODO  业务逻辑待对比
 */
@Service
public class PmsCommentServiceImpl implements PmsCommentService {

    @Autowired
    PmsCommentMapper commentMapper;

    /**
     * 新增评论
     * @param requesr
     * @return
     */
    @Override
    public CommonResult insertComment(PmsComment request) {
        request.setCreateTime(new Date());
        request.setShowStatus(0);
        request.setReadCount(1);
        request.setReplayCount(0);
        // 产品评价，默认话术
        if(StringUtils.isEmpty(request.getContent())){
            request.setContent("此商品很好，经济实惠!");
        }
        // 产品星数，默认五颗
        if(request.getStar().equals(null) || request.getStar() <= 0){
            request.setStar(5);
        }
        int result = commentMapper.insertSelective(request);
        if(result <= 0){
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    // TODO  排序 最近评论时间
    @Override
    public List<PmsComment> queryCommentList(PmsCommentReqDTO request) {
        List<PmsComment> pmsCommentList = new ArrayList<>();
        if(null != request){
            PmsCommentExample example = new PmsCommentExample();
            PmsCommentExample.Criteria criteria = example.createCriteria();
            criteria.andShowStatusEqualTo(0);
            if(StringUtils.isNotEmpty(request.getProductName())){
                criteria.andProductNameEqualTo(request.getProductName());
            }
            if(StringUtils.isNotEmpty(request.getMemberName())){
                criteria.andMemberNickNameEqualTo(request.getMemberName());
            }
            pmsCommentList = commentMapper.selectByExample(example);
            if(CollectionUtil.isNotEmpty(pmsCommentList)){
                return pmsCommentList;
            }
        }
        return pmsCommentList;
    }

    @Override
    public CommonResult bitchDelComment(PmsCommentReqDTO request) {
        if(CollectionUtil.isNotEmpty(request.getCommentIds())){
            PmsCommentExample example = new PmsCommentExample();
            example.createCriteria().andIdIn(request.getCommentIds());
            int result = commentMapper.deleteByExample(example);
            if(result >= 1){
                return CommonResult.success();
            }
        }
        return CommonResult.failed();
    }
}
