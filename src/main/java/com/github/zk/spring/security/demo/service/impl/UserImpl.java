package com.github.zk.spring.security.demo.service.impl;

import com.github.zk.spring.security.demo.dao.MockDao;
import com.github.zk.spring.security.demo.pojo.UserInfo;
import com.github.zk.spring.security.demo.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = mockDao.queryUserByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return userInfo;
    }
}
