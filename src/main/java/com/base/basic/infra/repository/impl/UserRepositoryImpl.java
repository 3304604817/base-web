package com.base.basic.infra.repository.impl;

import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.repository.UserRepository;
import com.base.basic.infra.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    @SuppressWarnings("All")
    private UserMapper userMapper;

    @Override
    public List<IamUser> list(IamUser dto){
        return userMapper.list(dto);
    }

    @Override
    public IamUser detail(Long userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser insert(IamUser iamUser){
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
