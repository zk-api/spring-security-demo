package com.github.zk.spring.security.demo.controller;

import com.github.zk.spring.security.demo.common.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录 请求处理
 *
 * @author zk
 * @date 2021/2/4 14:02
 */
@Controller
public class LoginController {

    @Value("${login}")
    private String loginPage;

    @RequestMapping("/login")
    public String login() {
        return "redirect:" + loginPage;
    }

    @RequestMapping("/invalid")
    public String invalid() {
        return "redirect:" + loginPage;
    }

    @ResponseBody
    @RequestMapping("/index")
    public Response index() {
        Response response = Response.getInstance();
        response.setOk(0, null, "登录成功", null);
        return response;
    }


}
