package com.base.basic.app.service.impl;

import com.base.basic.domain.vo.v0.CurrentUserVO;
import com.base.basic.infra.mapper.UserMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.base.basic.app.service.UserService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CurrentUserVO currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUserVO currentUserVO = (CurrentUserVO) authentication.getPrincipal();
        return currentUserVO;
    }

    @Override
    public PageInfo<IamUser> pageList(PageParmaters pageParmaters, IamUser dto){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> userRepository.list(dto));
    }

    @Override
    public IamUser detail(Long userId){
        return userMapper.detail(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser insert(IamUser iamUser){
        iamUser.setOrganizationId(Objects.isNull(iamUser.getOrganizationId()) ? 1L : iamUser.getOrganizationId());
        return userRepository.insert(iamUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<IamUser> batchInsert(List<IamUser> iamUsers){
        return userRepository.batchInsert(iamUsers);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enable(Long userId){
        IamUser user = new IamUser();
        user.setId(userId);
        user.setLocked(Boolean.TRUE);
        userMapper.updateOptional(user,IamUser.FIELD_IS_LOCKED);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disabled(Long userId){
        IamUser user = new IamUser();
        user.setId(userId);
        user.setLocked(Boolean.FALSE);
        userMapper.updateOptional(user,IamUser.FIELD_IS_LOCKED);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser update(IamUser iamUser){
        return userRepository.update(iamUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<IamUser> iamUsers){
        userRepository.batchDelete(iamUsers);
    }
}
