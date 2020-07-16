package com.ex.mall.dto.req;

import lombok.*;

import java.util.List;

/**
 * 商品评论DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PmsCommentReqDTO {

    /** 产品编号 */
    private String productId;

    /** 产品名称 */
    private String productName;

    /** 产品分类 */
    private String category;

    /** 会员编号 */
    private String memberId;

    /** 会员姓名 */
    private String memberName;

    /** 评论列表 */
    private List<Long> commentIds;
}
