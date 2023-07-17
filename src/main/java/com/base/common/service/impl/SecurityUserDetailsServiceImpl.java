package com.base.common.service.impl;

import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.vo.v0.CurrentUserVO;
import com.base.basic.infra.mapper.UserMapper;
import com.base.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    /**
     * 校验登录的用户密码是否正确
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IamUser currentUser = null;
        if(null == (currentUser = userMapper.selectOne(new IamUser(username, Boolean.TRUE, Boolean.FALSE)))){
            throw new BaseException("用户不存在或已冻结");
        }
        return new CurrentUserVO(currentUser.getLoginName(), currentUser.getRealName(), currentUser.getHashPassword(), currentUser.getPhone(), currentUser.getEmail(), Collections.emptySet());
    }
}
