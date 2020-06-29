package com.ex.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmsMemberReqDTO {

    private String telephone;

    private String authCode;

}
