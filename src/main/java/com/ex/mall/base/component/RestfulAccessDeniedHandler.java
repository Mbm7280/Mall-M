package com.ex.mall.base.component;

import cn.hutool.json.JSONUtil;
import com.ex.mall.base.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Package: com.ex.mall.base.component
* @ClassName: RestfulAccessDeniedHandler
* @Description: 当访问接口没有权限时，自定义的返回结果
* @Author: mbm
* @date: 2020/6/27 14:41
* @Version: 1.0
*/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
