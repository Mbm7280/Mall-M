package com.ex.mall.service.impl;

import com.ex.mall.nosql.document.MemberReadHistory;
import com.ex.mall.nosql.repository.MemberReadHistoryRepository;
import com.ex.mall.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @Package: com.ex.mall.service.impl
* @ClassName: MemberReadHistoryServiceImpl
* @Description: 会员浏览记录管理ServiceImpl
* @Author: mbm
* @date: 2020/7/11 16:08
* @Version: 1.0
*/
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;

    /**
     * @Author: mbm
     * @ClassName: MemberReadHistoryServiceImpl
     * @MethodName: create
     * @Param:  [memberReadHistory]
     * @return: int
     * @Description: 生产浏览记录
     * @date: 2020/7/11 16:12
     * @Version: 1.0
     */
    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    /**
     * @Author: mbm
     * @ClassName: MemberReadHistoryServiceImpl
     * @MethodName: del
     * @Param:  [ids]
     * @return: int
     * @Description: 批量删除浏览记录
     * @date: 2020/7/11 16:15
     * @Version: 1.0
     */
    @Override
    public int del(List<String> ids) {
        List<MemberReadHistory> delList = new ArrayList<>();
        for (String id:ids){
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            delList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(delList);
        return ids.size();
    }

    /**
     * @Author: mbm
     * @ClassName: MemberReadHistoryServiceImpl
     * @MethodName: findMemberReadList
     * @Param:  [memberId]
     * @return: java.util.List<com.ex.mall.nosql.document.MemberReadHistory>
     * @Description: 查询浏览记录倒序显示
     * @date: 2020/7/11 16:15
     * @Version: 1.0
     */
    @Override
    public List<MemberReadHistory> findMemberReadList(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
