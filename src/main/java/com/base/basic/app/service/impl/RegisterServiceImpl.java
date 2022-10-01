package com.base.basic.app.service.impl;

import com.base.basic.app.service.RegisterService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.infra.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/1 12:00
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser byEmail(IamUser iamUser) throws Exception {
        if(StringUtils.isEmpty(iamUser.getEmail())){
            throw new Exception("注册邮箱不能为空");
        }
        if(!StringUtils.equals(iamUser.getPwd(), iamUser.getConfirmPwd())){
            throw new Exception("密码不一致");
        }

        Example example = new Example(IamUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(IamUser.FIELD_EMAIL, iamUser.getEmail());
        if(userMapper.selectCountByExample(example) > 0){
            throw new Exception("邮箱已注册");
        }

        iamUser.setLoginName(iamUser.getEmail());
        iamUser.setHashPassword(BCrypt.hashpw(iamUser.getPwd(), BCrypt.gensalt()));
        userMapper.insertSelective(iamUser);

        return iamUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser byPhone(IamUser iamUser) throws Exception {
        if(StringUtils.isEmpty(iamUser.getPhone())){
            throw new Exception("注册手机不能为空");
        }
        if(!StringUtils.equals(iamUser.getPwd(), iamUser.getConfirmPwd())){
            throw new Exception("密码不一致");
        }

        Example example = new Example(IamUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(IamUser.FIELD_PHONE, iamUser.getPhone());
        if(userMapper.selectCountByExample(example) > 0){
            throw new Exception("该手机号已注册");
        }

        iamUser.setLoginName(iamUser.getPhone());
        iamUser.setHashPassword(BCrypt.hashpw(iamUser.getPwd(), BCrypt.gensalt()));
        userMapper.insertSelective(iamUser);

        return iamUser;
    }
}
