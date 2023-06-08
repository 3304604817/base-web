package com.base.basic;

import com.base.common.util.convert.DateConvertUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BasicTest {

    @Test
    public void test(){
        System.out.println(DateConvertUtil.nowTimeString());
    }
}
