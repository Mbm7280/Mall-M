package com.ex.mall.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @Package: com.ex.mall.base
* @ClassName: CommonResult
* @Description: 架构封装
 *              -- 统一返回结果
* @Author: mbm
* @date: 2020/6/26 21:22
* @Version: 1.0
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

   private Integer code;

   private String message;

   private T data;

   /**
    * @author mbm X
    * @methodname : success
    * @description : 成功
    *               -- 响应
    * @return : com.ex.mall.base.CommonResult<T>
    * @date : 2020/6/27 10:31
    */
   public static <T> CommonResult<T> success(){
       return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),null);
   }

    /**
     * @author mbm X
     * @methodname : success
     * @description : 成功
     *                  -- 自定义返回结果
     * @param data : 
     * @return : com.ex.mall.base.CommonResult<T> 
     * @date : 2020/6/26 21:32
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
    * @Package: com.ex.mall.base
    * @ClassName: CommonResult
    * @Description: 成功
     *              -- 自定义返回结果和消息
    * @Author: mbm
    * @date: 2020/6/26 21:35
    * @Version: 1.0
    */
    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * @author mbm X
     * @methodname : failed
     * @description : 失败
     *                 -- 返回状态码 和 消息
     * @param errorCode :
     * @return : com.ex.mall.base.CommonResult<T>
     * @date : 2020/6/26 21:50
     */
   public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<T>(errorCode.getCode(),errorCode.getMessage(),null);
   }

   /**
    * @author mbm X
    * @methodname : failed
    * @description : 失败
    *                -- 自定义返回消息
    * @param message :
    * @return : com.ex.mall.base.CommonResult<T>
    * @date : 2020/6/26 21:52
    */
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),message,null);
    }

    /**
     * @author mbm X
     * @methodname : failed
     * @description : 失败
     *              -- 返回自定义状态和消息
     * @return : com.ex.mall.base.CommonResult<T>
     * @date : 2020/6/26 21:55
     */
    public static <T> CommonResult<T> failed(){
        return failed(ResultCode.FAILED);
    }

    /**
     * @author mbm X
     * @methodname : validateFailed
     * @description : 失败
     *                  -- 参数校验失败
     * @return : com.ex.mall.base.CommonResult<T>
     * @date : 2020/6/26 21:57
     */
    public static <T> CommonResult<T> validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * @author mbm X
     * @methodname : validateFailed
     * @description : 失败
     *                  --  自定义参数校验失败返回消息
     * @param message :
     * @return : com.ex.mall.base.CommonResult<T>
     * @date : 2020/6/26 22:00
     */
    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(),message,null);
    }

    /**
     * @author mbm X
     * @methodname : unauthorized
     * @description : 失败
     *                  -- 暂未登陆或则Token过期
     * @param data :
     * @return : com.ex.mall.base.CommonResult<T>
     * @date : 2020/6/26 22:01
     */
    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(),data);
    }

    /**
     * @author mbm X
     * @methodname : forbidden
     * @description : 失败
     *                  -- 暂无此权限
     * @param data :
     * @return : com.ex.mall.base.CommonResult<T>
     * @date : 2020/6/26 22:02
     */
    public static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),data);
    }


}
