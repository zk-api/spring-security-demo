package com.github.zk.spring.security.demo.controller;

import com.github.zk.spring.security.demo.pojo.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 *
 * @author zk
 * @date 2021/1/15 19:24
 */
@Controller
@RequestMapping("/main")
public class MainController {

    /**
     * 登录页
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/login.html");
    }

    /**
     * 登录后首页
     * @return
     */
    @ResponseBody
    @RequestMapping("/publicRequest")
    public String publicRequest() {
        return "I`m public page.";
    }
    /**
     * 登录后首页
     * @return
     */
    @ResponseBody
    @RequestMapping("/index")
    public String index() {
//        return "redirect:http://www.baidu.com";
        return "首页";
    }

    /**
     * 登录后具备此权限
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryUser")
    public String queryUser() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ObjectUtils.nullSafeToString(userInfo);
    }

    /**
     * admin角色具有的权限
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAdmin")
    public String queryAdmin() {
        return ObjectUtils.nullSafeToString("I`m admin");
    }

    @RequestMapping("/invalid")
    public ModelAndView invalid() {
        return new ModelAndView("/login.html");
    }
}
