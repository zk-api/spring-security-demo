package com.github.zk.spring.security.demo.config;

import com.github.zk.spring.security.demo.handler.CustomAccessDecisionManagerHandler;
import com.github.zk.spring.security.demo.handler.CustomAuthenticationFailureHandler;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Security 配置
 *
 * @author zk
 * @date 2021/1/13 17:16
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //取消跨站防护
        http.csrf().disable().cors().disable();
        http.authorizeRequests((requests) -> requests
                .antMatchers(
                        //免登录公开请求
                        "/main/publicRequest",
                        //登录地址，用于直接访问登录接口，跳转登录界面
                        "/login",
                        //Session 失效请求
                        "/invalid").permitAll()
                //其他任意请求都需要登录验证
                .anyRequest().authenticated()
                //后置处理器
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        //设置自定义授权处理器
                        fsi.setAccessDecisionManager(new CustomAccessDecisionManagerHandler());
                        return fsi;
                    }
                }));
        // POST请求 /login
        http.formLogin()
                //登录服务地址
                .loginPage("/login")
                .successForwardUrl("/index")
                .failureHandler(new CustomAuthenticationFailureHandler());
        http.httpBasic();
        http.sessionManagement()
                //session 失效处理
                .invalidSessionUrl("/invalid")
                //单点登录
                .maximumSessions(1);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/error");
    }
}
