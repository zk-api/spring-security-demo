package com.github.zk.spring.security.demo.property;

import com.github.zk.spring.security.demo.pojo.UserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 登录配置
 *
 * @author zk
 * @date 2021/2/24 17:36
 */
@Configuration
@ConfigurationProperties("login")
public class LoginProperties {

    private UserInfo user;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
