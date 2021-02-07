package com.github.zk.spring.security.demo.pojo;

import java.io.Serializable;

/**
 * 权限信息
 *
 * @author zk
 * @date 2021/1/21 10:41
 */
public class PermissionInfo implements Serializable {
    private static final long serialVersionUID = 874671003093440548L;

    private Long id;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PermissionInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
