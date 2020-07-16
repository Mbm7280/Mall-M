package com.ex.mall.dto.other;

import lombok.*;

/**
 * 分页查询DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PageReqDTO {

    private String keyword;

    private Integer pageNum;

    private Integer pageSize;

}
