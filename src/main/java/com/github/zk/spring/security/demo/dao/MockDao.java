package com.github.zk.spring.security.demo.dao;

import com.github.zk.spring.security.demo.pojo.PermissionInfo;
import com.github.zk.spring.security.demo.pojo.RoleInfo;
import com.github.zk.spring.security.demo.pojo.UserInfo;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据库
 *
 * @author zk
 * @date 2021/1/15 14:37
 */
@Repository
public class MockDao {

    public UserInfo queryUserByUsername(String username) {
        Map<String, UserInfo> map = new HashMap<>();
        //创建用户信息
        UserInfo adminInfo = new UserInfo();
        adminInfo.setId(1L);
        adminInfo.setUsername("admin");

        //创建admin角色信息
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setId(1L);
        roleInfo.setRoleName("admin");
        //设置角色信息
        adminInfo.setRoles(Collections.singletonList(roleInfo));

        //创建权限信息
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setId(1L);
        permissionInfo.setUrl("/main/queryUser");

        PermissionInfo permissionInfo2 = new PermissionInfo();
        permissionInfo2.setId(2L);
        permissionInfo2.setUrl("/main/queryAdmin");
        //设置权限信息
        roleInfo.setPermissionInfos(Arrays.asList(permissionInfo, permissionInfo2));

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = passwordEncoder.encode("1");
        adminInfo.setPassword(password);
        map.put("admin", adminInfo);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(2L);
        userInfo.setUsername("user");
        userInfo.setPassword(password);

        //创建user角色信息
        RoleInfo userRoleInfo = new RoleInfo();
        userRoleInfo.setId(2L);
        userRoleInfo.setRoleName("user");
        //设置角色信息
        userInfo.setRoles(Collections.singletonList(userRoleInfo));

        //创建权限信息
        PermissionInfo userPermissionInfo = new PermissionInfo();
        userPermissionInfo.setId(3L);
        userPermissionInfo.setUrl("/main/queryUser");
        //设置权限信息
        userRoleInfo.setPermissionInfos(Collections.singletonList(userPermissionInfo));

        map.put("user", userInfo);
        return map.get(username);
    }
}
