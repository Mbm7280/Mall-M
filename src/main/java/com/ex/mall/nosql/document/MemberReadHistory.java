package com.ex.mall.nosql.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
* @Package: com.ex.mall.nosql
* @ClassName: MemberReadHistory
* @Description: 用户商品浏览历史记录
* @Author: mbm
* @date: 2020/7/11 15:54
* @Version: 1.0
*/
@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadHistory {


    @Id
    private String id;

    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;

    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;


}
