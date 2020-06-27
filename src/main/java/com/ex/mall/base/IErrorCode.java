package com.ex.mall.base;

/**
* @Package: com.ex.mall.base
* @ClassName: IErrorCode
* @Description: 统一返回结果
 *              -- 状态码
* @Author: mbm
* @date: 2020/6/26 21:48
* @Version: 1.0
*/
public interface IErrorCode {

    Integer getCode();

    String getMessage();

}
