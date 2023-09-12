package com.base.basic.app.service.impl;

import com.base.basic.domain.vo.v0.CurrentUserVO;
import com.base.basic.infra.mapper.UserMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.base.basic.app.service.UserService;
import com.base.basic.domain.entity.v0.IamUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @Override
    public CurrentUserVO currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUserVO currentUserVO = (CurrentUserVO) authentication.getPrincipal();
        return currentUserVO;
    }

    @Override
    public PageInfo<IamUser> pageList(PageParmaters pageParmaters, IamUser dto){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> userMapper.list(dto));
    }

    @Override
    public IamUser detail(Long userId){
        return userMapper.detail(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser insert(IamUser iamUser){
        iamUser.setOrganizationId(Objects.isNull(iamUser.getOrganizationId()) ? 1L : iamUser.getOrganizationId());
        userMapper.insertSelective(iamUser);
        return iamUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<IamUser> batchInsert(List<IamUser> iamUsers){
        for(IamUser user:iamUsers){
            userMapper.insertSelective(user);
        }
        return iamUsers;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean resetPassword(Long userId, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashpw = passwordEncoder.encode(password);
        IamUser user = new IamUser();
        user.setId(userId);
        user.setHashPassword(hashpw);
        userMapper.updateOptional(user, IamUser.FIELD_HASH_PASSWORD);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enable(Long userId){
        IamUser user = new IamUser();
        user.setId(userId);
        user.setIsEnabled(Boolean.TRUE);
        userMapper.updateOptional(user,IamUser.FIELD_IS_ENABLED);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disabled(Long userId){
        IamUser user = new IamUser();
        user.setId(userId);
        user.setIsEnabled(Boolean.FALSE);
        userMapper.updateOptional(user,IamUser.FIELD_IS_ENABLED);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser update(IamUser iamUser){
        int i = userMapper.updateOptionalOpl(iamUser, IamUser.FIELD_EMAIL);
        return iamUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<IamUser> iamUsers){
        iamUsers.stream().forEach(p -> {
            userMapper.deleteByPrimaryKey(p.getId());
        });
    }

    /**
     * 重写 easySave 方法
     * @param list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void easySave(List<Object> list) {
        list.stream().forEach(data -> {
            IamUser user = new IamUser();
            BeanUtils.copyProperties(data, user);
            userMapper.insertSelective(user);
        });
    }
}
