package com.ex.mall.base;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* @Package: com.ex.mall.base
* @ClassName: CommonPage
* @Description: 封装
 *              -- 分页插件
* @Author: mbm
* @date: 2020/6/26 22:05
* @Version: 1.0
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;


    /**
     * @author mbm X
     * @methodname : restPage
     * @description : 封装
     *                  -- 分页插件封装
     * @param list :
     * @return : com.ex.mall.base.CommonPage<T>
     * @date : 2020/6/26 22:14
     */
    public static <T> CommonPage<T> restPage(List<T> list){
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());
        result.setPageSize(pageInfo.getPageSize());
        result.setPageNum(pageInfo.getPageNum());
        result.setList(pageInfo.getList());
        return result;
    }


}
