package com.github.zk.spring.security.demo.handler;

import com.alibaba.fastjson.JSON;
import com.github.zk.spring.security.demo.common.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义异常处理器
 *
 * @author zk
 * @date 2021/1/26 17:41
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException {
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        Response response = Response.getInstance();
        response.setError(Response.CodeEnum.FAIL, null, exception.getMessage());
        out.write(JSON.toJSONString(response));
        out.flush();
        out.close();
    }
}
