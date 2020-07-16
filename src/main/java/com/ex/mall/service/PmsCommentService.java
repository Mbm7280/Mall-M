package com.ex.mall.service;

import com.ex.mall.base.CommonResult;
import com.ex.mall.dto.req.PmsCommentReqDTO;
import com.ex.mall.model.PmsComment;

import java.util.List;

/**
 * 商品评论
 */
public interface PmsCommentService {

    /**
     * 新增评论
     */
    CommonResult insertComment(PmsComment request);

    /**
     * 评论展示
     * 模糊查询评论
     * PmsCommentReqDTO
     */
    List<PmsComment> queryCommentList(PmsCommentReqDTO request);

    /**
     * 批量删除
     */
    CommonResult bitchDelComment(PmsCommentReqDTO request);

}
