package com.ex.mall.service;

/**
* @Package: com.ex.mall.service
* @ClassName: RedisService
* @Description: redis
 *              -- 操作
* @Author: mbm
* @date: 2020/6/29 22:02
* @Version: 1.0
*/
public interface RedisService {

  /**
   * @author mbm X
   * @methodname : set
   * @description : set--操作
   * @param key :
   * @param value :
   * @return : void
   * @date : 2020/6/29 22:04
   */
    void set(String key,String value);

    /**
     * @author mbm X
     * @methodname : get
     * @description : get--操作
     * @param key :
     * @return : java.lang.String
     * @date : 2020/6/29 22:04
     */
    String get(String key);

    /**
     * @author mbm X
     * @methodname : expire
     * @description : expire--操作
     * @param key :
     * @param expire :
     * @return : boolean
     * @date : 2020/6/29 22:05
     */
    boolean expire(String key,long expire);

    /**
     * @author mbm X
     * @methodname : remove
     * @description : del-操作
     * @param key :
     * @return : void
     * @date : 2020/6/29 22:06
     */
    void remove(String key);

    /**
     * @author mbm X
     * @methodname : increment
     * @description : increment--操作
     * @param key :
     * @param delta :
     * @return : java.lang.Long
     * @date : 2020/6/29 22:06
     */
    Long increment(String key,long delta);

}
