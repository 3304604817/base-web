package com.base.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BCryptTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void test(){
        /**
         * 加密
         * BCrypt.gensalt() 生成随机盐(可以指定固定的随机盐)
         */
        String pwd1 = BCrypt.hashpw("Admin@123!", BCrypt.gensalt());
        String pwd2 = passwordEncoder.encode("Admin@123!");

        /**
         * 密码校验
         */
        boolean check1 = BCrypt.checkpw("Admin@123!", pwd1);
        boolean check2 = BCrypt.checkpw("Admin@123!", pwd2);
        return;
    }
}
