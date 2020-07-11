package com.ex.mall.nosql.repository;

import com.ex.mall.nosql.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
* @Package: com.ex.mall.nosql.repository
* @ClassName: MemberReadHistoryRepository
* @Description: 会员浏览商品Repository
* @Author: mbm
* @date: 2020/7/11 16:00
* @Version: 1.0
*/
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {

    /**
     * 根据会员Id倒序获取浏览记录
     * @param memeberId
     * @return
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memeberId);

}
