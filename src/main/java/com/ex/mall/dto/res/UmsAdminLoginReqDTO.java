package com.ex.mall.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @Package: com.ex.mall.dto
* @ClassName: UmsAdminLoginReqDTO
* @Description: DTO
 *              用户管理请求Dto
* @Author: mbm
* @date: 2020/6/27 16:52
* @Version: 1.0
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmsAdminLoginReqDTO {

    private String username;

    private String password;

}
