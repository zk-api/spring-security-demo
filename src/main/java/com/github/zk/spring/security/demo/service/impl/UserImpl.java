package com.github.zk.spring.security.demo.service.impl;

import com.github.zk.spring.security.demo.dao.MockDao;
import com.github.zk.spring.security.demo.dao.PermissionMapper;
import com.github.zk.spring.security.demo.dao.UserMapper;
import com.github.zk.spring.security.demo.pojo.PermissionInfo;
import com.github.zk.spring.security.demo.pojo.UserInfo;
import com.github.zk.spring.security.demo.property.LoginProperties;
import com.github.zk.spring.security.demo.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 用户接口实现
 *
 * @author zk
 * @date 2021/1/15 14:36
 */
@Service
public class UserImpl implements IUser, UserDetailsService {

    @Autowired
    private MockDao mockDao;
    @Autowired
    private LoginProperties properties;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        if (ObjectUtils.nullSafeEquals(properties.getUser().getUsername(), username)) {
            //超级管理员
            userInfo = properties.getUser();
            //查询所有权限
            List<PermissionInfo> permissionInfos = permissionMapper.selectList(null);
            //将权限写入角色
            userInfo.getRoles().get(0).setPermissionInfos(permissionInfos);
            return userInfo;
        }
        UserInfo user = new UserInfo();
        user.setUsername(username);
//        userInfo = userMapper.selectOne(new QueryWrapper<>(user, "id", "username", "password"));
        userInfo = userMapper.selectUser(user);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return userInfo;
    }
}
