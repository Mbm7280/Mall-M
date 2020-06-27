package com.ex.mall.base;

/**
* @Package: com.ex.mall.base
* @ClassName: ResultCode
* @Description: 统一返回结果
 *              -- EnumCode
* @Author: mbm
* @date: 2020/6/26 21:25
* @Version: 1.0
*/
public enum ResultCode implements IErrorCode {

    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404,"参数校验失败"),
    UNAUTHORIZED(401,"暂未登陆或则Token过期"),
    FORBIDDEN(403,"暂无此权限");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
