package com.ex.mall.service;

import com.ex.mall.nosql.document.MemberReadHistory;

import java.util.List;

/**
* @Package: com.ex.mall.service
* @ClassName: MemberReadHistoryService
* @Description: 会员浏览记录管理Service
* @Author: mbm
* @date: 2020/7/11 16:04
* @Version: 1.0
*/
public interface MemberReadHistoryService {

    /**
     * 生成浏览记录
     * @param memberReadHistory
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     * @param ids
     * @return
     */
    int del(List<String> ids);

    /**
     * 获取浏览记录
     * @param memberId
     * @return
     */
    List<MemberReadHistory> findMemberReadList(Long memberId);

}
