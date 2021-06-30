package com.github.zk.spring.security.demo.handler;

import com.github.zk.spring.security.demo.pojo.PermissionInfo;
import com.github.zk.spring.security.demo.pojo.RoleInfo;
import com.github.zk.spring.security.demo.pojo.UserInfo;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

/**
 * 授权管理处理器
 * 处理每一个请求是否具备权限
 *
 * @author zk
 * @date 2021/1/18 17:57
 */
public class CustomAccessDecisionManagerHandler implements AccessDecisionManager {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        System.out.println("访问地址：" + requestUrl);
        //处理授权公开（permitAll）请求
        for (ConfigAttribute configAttribute : configAttributes) {
            if (ObjectUtils.nullSafeEquals("permitAll", configAttribute.toString())) {
                return;
            }
        }
        //处理登录
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException("未登录");
        }
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        System.out.printf("用户名【%s】,角色【%s】\n", userInfo.getUsername(), userInfo.getRoles());
        //角色的url权限过滤
        for (RoleInfo role : userInfo.getRoles()) {
            for (PermissionInfo permissionInfo : role.getPermissionInfos()) {
                boolean match = ANT_PATH_MATCHER.match(permissionInfo.getUrl(), requestUrl);
                if (match) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
